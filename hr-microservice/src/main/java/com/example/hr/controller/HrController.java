package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.FireEmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.dto.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.hr.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin("*")
@Validated
public class HrController {
	
	private HrService hrService;

	@Autowired
	public HrController(HrService hrService) {
		this.hrService = hrService;
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("/{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable("identity") @Validated @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
	}
}
