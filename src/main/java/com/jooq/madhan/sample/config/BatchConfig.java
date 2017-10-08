package com.jooq.madhan.sample.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.jooq.madhan.batch.model.RevenueDetailJobParam;
import com.jooq.madhan.batch.tasklet.RunStatusTasklet;
import com.jooq.madhan.batch.tasklet.ScheduleZeusJobTasklet;
import com.jooq.madhan.sample.batch.listener.ApolloRevenueDetailRecognitionCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
	
	public static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	  
    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    DataSource dataSource;
    
    public static final FlowExecutionStatus PROCEED = new FlowExecutionStatus("PROCEED");
    
    public static final FlowExecutionStatus STOP = new FlowExecutionStatus("STOP");
    
    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(
            ResourcelessTransactionManager txManager) throws Exception {
        
        MapJobRepositoryFactoryBean factory = new 
                MapJobRepositoryFactoryBean(txManager);
        
        factory.afterPropertiesSet();
        
        return factory;
    }

    @Bean
    public JobRepository jobRepository(
            MapJobRepositoryFactoryBean factory) throws Exception {
        return factory.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
    
    @Scheduled(cron = "0/10 52 21 * * ?")
    public void trigger() throws Exception {
    	JobParameters jobParameters = new JobParametersBuilder().addString("JobID",String.valueOf(System.currentTimeMillis())).toJobParameters();
		JobExecution execution = jobLauncher.run(apolloRevenueDetailRecognitionJob(), jobParameters);
		logger.info("Exit status : " + execution.getStatus());
    }
    
    @Bean
    public Job apolloRevenueDetailRecognitionJob() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
    	 JobBuilder jobBuilder = jobBuilderFactory.get("apolloRevenueDetailRecognitionJob").incrementer(new RunIdIncrementer()).listener(listener());
    	 JobFlowBuilder jobFlowBuilder = jobBuilder.flow(checkRunStatus());
    	 jobFlowBuilder.from(checkRunStatus()).next(runStatusDecider());
    	 jobFlowBuilder.from(runStatusDecider()).on("PROCEED").to(scheduleZeusJob());
    	 jobFlowBuilder.from(runStatusDecider()).on("STOP").end();
    	 return jobFlowBuilder.build().build();
    }
    
    @Bean
    public Step checkRunStatus() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
        return stepBuilderFactory.get("checkRunStatus").tasklet(new RunStatusTasklet())
                .build();
    }
    
    @Bean
    public Step scheduleZeusJob() {
        return stepBuilderFactory.get("scheduleZeusJob").tasklet(new ScheduleZeusJobTasklet())
                .build();
    }
    
    @Bean
    public JobExecutionListener listener() {
        return new ApolloRevenueDetailRecognitionCompletionNotificationListener();
    }
    
    @Bean
    public JobExecutionDecider runStatusDecider() {
        return new JobExecutionDecider() {
            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        		RevenueDetailJobParam revenueDetailJobParam = (RevenueDetailJobParam) jobExecution.getExecutionContext().get("revenueDetailJobParam");
        		logger.info("RUN STATUS IN DECIDER: "+revenueDetailJobParam.isRunStatus()+" Number of Records in decider: "+revenueDetailJobParam.getNumberOfRecords());
                if(revenueDetailJobParam.isRunStatus()) {
                    return PROCEED;
                } else {
                    return STOP;
                }
            }
        };
    }
}
