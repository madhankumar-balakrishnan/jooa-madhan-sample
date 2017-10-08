package com.jooq.madhan.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jooq.madhan.batch.model.RevenueDetailJobParam;

public class RevenueDetailJobParamRowMapper implements RowMapper<RevenueDetailJobParam>{
	@Override
	public RevenueDetailJobParam mapRow(ResultSet rs, int rowNum) throws SQLException {
		RevenueDetailJobParam revenueDetailJobParam = new RevenueDetailJobParam();
		revenueDetailJobParam.setId(rs.getInt("id"));
		revenueDetailJobParam.setNumberOfRecords(rs.getInt("numberOfRecords"));
		revenueDetailJobParam.setRunStatus(rs.getBoolean("runStatus"));
		return revenueDetailJobParam;
	}
}
