package com.example.hr.events;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {

	private TcKimlikNo tcKimlikNo;

	public EmployeeEvent(TcKimlikNo tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}
}
