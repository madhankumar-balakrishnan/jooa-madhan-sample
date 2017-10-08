package com.jooq.madhan.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import com.jooq.madhan.batch.model.RevenueDetailJobParam;

public class RunStatusDecider implements JobExecutionDecider{
	
	public static final Logger logger = LoggerFactory.getLogger(RunStatusDecider.class);

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
		RevenueDetailJobParam revenueDetailJobParam = (RevenueDetailJobParam) jobExecution.getExecutionContext().get("revenueDetailJobParam");
		logger.info("RUN STATUS IN DECIDER: "+revenueDetailJobParam.isRunStatus()+" Number of Records in decider: "+revenueDetailJobParam.getNumberOfRecords());

		return null;
	}

}
