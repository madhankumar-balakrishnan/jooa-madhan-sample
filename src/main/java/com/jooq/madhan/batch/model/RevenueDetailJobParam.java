package com.jooq.madhan.batch.model;

public class RevenueDetailJobParam {
	
	private int id;
	private int numberOfRecords;
	private boolean runStatus;
	
	public RevenueDetailJobParam() {
		super();
	}

	public RevenueDetailJobParam(int id, int numberOfRecords, boolean runStatus) {
		super();
		this.id = id;
		this.numberOfRecords = numberOfRecords;
		this.runStatus = runStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public boolean isRunStatus() {
		return runStatus;
	}

	public void setRunStatus(boolean runStatus) {
		this.runStatus = runStatus;
	}

	@Override
	public String toString() {
		return "RevenueDetailJobParam [id=" + id + ", numberOfRecords="
				+ numberOfRecords + ", runStatus=" + runStatus + "]";
	}
}
