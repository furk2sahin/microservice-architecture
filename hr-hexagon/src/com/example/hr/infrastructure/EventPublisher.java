package com.example.hr.infrastructure;

import com.example.hr.events.EmployeeEvent;

public interface EventPublisher {

	void publish(EmployeeEvent event);

}
