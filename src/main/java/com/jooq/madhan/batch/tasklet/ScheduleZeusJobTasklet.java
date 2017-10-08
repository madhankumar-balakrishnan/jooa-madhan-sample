package com.jooq.madhan.batch.tasklet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jooq.madhan.batch.model.RevenueDetailJobParam;
import com.jooq.madhan.sample.repository.RevenueDetailJobParamRowMapper;

public class ScheduleZeusJobTasklet implements Tasklet {
	
	public static final Logger logger = LoggerFactory.getLogger(ScheduleZeusJobTasklet.class);
	
	@Autowired
	DataSource dataSource;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		RevenueDetailJobParam revenueDetailJobParam = (RevenueDetailJobParam) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("revenueDetailJobParam");
		logger.info("RUN STATUS: "+revenueDetailJobParam.isRunStatus()+" Number of Records: "+revenueDetailJobParam.getNumberOfRecords());
		return RepeatStatus.FINISHED;
	}
}
