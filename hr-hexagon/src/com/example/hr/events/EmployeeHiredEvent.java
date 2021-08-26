package com.example.hr.events;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends EmployeeEvent{

	public EmployeeHiredEvent(TcKimlikNo tcKimlikNo) {
		super(tcKimlikNo); 
	}

}
