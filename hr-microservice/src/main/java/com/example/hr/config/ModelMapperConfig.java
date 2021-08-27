package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.EmployeeKafkaEvent;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.events.EmployeeEvent;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(employeeDocument2Employee, EmployeeDocument.class, Employee.class);
		mapper.addConverter(employee2EmployeeDocument, Employee.class, EmployeeDocument.class);
		return mapper;
	}
	
	private static final Converter<EmployeeDocument, Employee> employeeDocument2Employee
		= context -> {
			var empDoc = context.getSource();
			return new Employee.Builder(TcKimlikNo.valueOf(empDoc.getKimlikNo()))
					.fullname(empDoc.getFullname())
					.iban(empDoc.getIban())
					.salary(empDoc.getSalary(), FiatCurrency.TRY)
					.birthYear(empDoc.getBirthYear())
					.jobType(empDoc.getJobType().name())
					.department(empDoc.getDepartment().name())
					.photo(empDoc.getPhoto().getBytes())
					.build();
		};
	
	private static final Converter<Employee, EmployeeDocument> employee2EmployeeDocument
		= context -> {
			var employee = context.getSource();
			var employeeDocument = new EmployeeDocument();
			var fullname = employee.getFullname();
			employeeDocument.setKimlikNo(employee.getKimlikNo().getValue());
			employeeDocument.setFullname(fullname.getFirstName() + " " + fullname.getLastName());
			employeeDocument.setSalary(employee.getSalary().getValue());
			employeeDocument.setBirthYear(employee.getBirthYear().getValue());
			employeeDocument.setIban(employee.getIban().getValue());
			employeeDocument.setDepartment(employee.getDepartment());
			employeeDocument.setJobType(employee.getJobType());
			employeeDocument.setPhoto(new String(employee.getPhoto().getData()));
			return employeeDocument;
	};
	
	private static final Converter<EmployeeEvent, EmployeeKafkaEvent> employeeEvent2EmployeeKafkaEvent
	= context -> {
		var employeeEvent = context.getSource();
		return new EmployeeKafkaEvent(employeeEvent.getTcKimlikNo().getValue());
	};
	
	private static final Converter<EmployeeEntity, Employee> employeeEntity2Employee
	= context -> {
		var empDoc = context.getSource();
		return new Employee.Builder(TcKimlikNo.valueOf(empDoc.getKimlikNo()))
				.fullname(empDoc.getFullname())
				.iban(empDoc.getIban())
				.salary(empDoc.getSalary(), FiatCurrency.TRY)
				.birthYear(empDoc.getBirthYear())
				.jobType(empDoc.getJobType().name())
				.department(empDoc.getDepartment().name())
				.photo(empDoc.getPhoto())
				.build();
	};
	
	private static final Converter<Employee, EmployeeEntity> employee2EmployeeEntity
	= context -> {
		var employee = context.getSource();
		var employeeEntity = new EmployeeEntity();
		var fullname = employee.getFullname();
		employeeEntity.setKimlikNo(employee.getKimlikNo().getValue());
		employeeEntity.setFullname(fullname.getFirstName() + " " + fullname.getLastName());
		employeeEntity.setSalary(employee.getSalary().getValue());
		employeeEntity.setBirthYear(employee.getBirthYear().getValue());
		employeeEntity.setIban(employee.getIban().getValue());
		employeeEntity.setDepartment(employee.getDepartment());
		employeeEntity.setJobType(employee.getJobType());
		employeeEntity.setPhoto(employee.getPhoto().getData());
		return employeeEntity;
	};
}
