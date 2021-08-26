package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name="database", havingValue = "mongo")
public class MongoRepositoryEmployeeAdapter implements EmployeeRepository{
	
	private EmployeeDocumentRepository employeeDocumentRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public MongoRepositoryEmployeeAdapter(EmployeeDocumentRepository employeeDocumentRepository,
			ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return employeeDocumentRepository.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var employeeDocument = modelMapper.map(employee, EmployeeDocument.class);
		var persistedEmployee = employeeDocumentRepository.save(employeeDocument);
		return modelMapper.map(persistedEmployee, Employee.class);
	}

	@Override
	public Optional<Employee> removeByIdentity(TcKimlikNo tcKimlikNo) {
		var employeeDocument = employeeDocumentRepository.findById(tcKimlikNo.getValue());
		if(employeeDocument.isEmpty()) {
			return Optional.empty();
		}
		EmployeeDocument empDoc = employeeDocument.get();
		employeeDocumentRepository.delete(empDoc);
		var employee = modelMapper.map(empDoc, Employee.class);
		return Optional.of(employee);
	}
	
	
	
}
