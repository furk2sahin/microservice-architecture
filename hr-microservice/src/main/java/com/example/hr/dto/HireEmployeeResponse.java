package com.example.hr.dto;

public class HireEmployeeResponse {
	private String status;
	public HireEmployeeResponse(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
