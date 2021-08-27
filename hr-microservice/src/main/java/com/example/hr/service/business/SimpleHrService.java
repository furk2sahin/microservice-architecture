package com.example.hr.service.business;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.FireEmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.dto.HireEmployeeResponse;
import com.example.hr.service.HrService;

@Service
public class SimpleHrService implements HrService{

	@Autowired
	private HrApplication hrApplication;
	
	@Override
	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var photo = request.getPhoto();
		byte[] data = null;
		if(Objects.nonNull(photo)) {
			data = photo.getBytes();
		}
		TcKimlikNo kimlikNo = TcKimlikNo.valueOf(request.getIdentity());
		Employee employee = new Employee.Builder(kimlikNo)
				.fullname(request.getFirstName(), request.getLastName())
				.iban(request.getIban())
				.salary(request.getSalary(), FiatCurrency.TRY)
				.department(request.getDepartment().name())
				.jobType(request.getType().name())
				.photo(data)
				.birthYear(request.getBirthYear())
				.build();
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	@Override
	@Transactional
	public FireEmployeeResponse fireEmployee(String identity) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity));
		if(employee.isEmpty())  {
			return new FireEmployeeResponse("failed", "Cannot find employee to fire.");
		}
		return new FireEmployeeResponse("success");
	}
}
