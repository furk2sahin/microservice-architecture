package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.events.EmployeeFiredEvent;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication{

	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getKimlikNo();
		if(employeeRepository.existsByIdentity(identity)) {
			throw new IllegalArgumentException("Employee already exists.");
		}
		Employee savedEmployee = employeeRepository.persist(employee);
		eventPublisher.publish(new EmployeeHiredEvent(identity));
		return savedEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo tcKimlikNo) {
		Optional<Employee> employee = employeeRepository.removeByIdentity(tcKimlikNo);
		if(employee.isPresent()) {
			eventPublisher.publish(new EmployeeFiredEvent(tcKimlikNo));
		}
		return employee;
	}

}
