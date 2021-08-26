package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name="database", havingValue = "jpa")
public class JpaRepositoryEmployeeAdapter implements EmployeeRepository{

	private EmployeeEntityRepository employeeEntityRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public JpaRepositoryEmployeeAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var employeeDocument = modelMapper.map(employee, EmployeeEntity.class);
		var persistedEmployee = employeeEntityRepository.save(employeeDocument);
		return modelMapper.map(persistedEmployee, Employee.class);
	}

	@Override
	public Optional<Employee> removeByIdentity(TcKimlikNo tcKimlikNo) {
		var employeeDocument = employeeEntityRepository.findById(tcKimlikNo.getValue());
		if(employeeDocument.isEmpty()) {
			return Optional.empty();
		}
		EmployeeEntity emp = employeeDocument.get();
		employeeEntityRepository.delete(emp);
		var employee = modelMapper.map(emp, Employee.class);
		return Optional.of(employee);
	}
}