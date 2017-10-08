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

public class RunStatusTasklet implements Tasklet {
	
	public static final Logger logger = LoggerFactory.getLogger(RunStatusTasklet.class);
	
	@Autowired
	DataSource dataSource;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		RevenueDetailJobParam revenueDetailJobParam = jdbcTemplate.queryForObject("SELECT id as id, number_of_records as numberOfRecords,run_status as runStatus FROM REVENUE_DETAIL_JOB_PARAMETERS WHERE id=1",
				new RevenueDetailJobParamRowMapper());
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("revenueDetailJobParam", revenueDetailJobParam);
		return RepeatStatus.FINISHED;
	}
}
