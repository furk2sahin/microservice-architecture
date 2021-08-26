package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmService {
	@Autowired
	private CustomerDocumentReactiveRepository customerRepository;

	public Mono<CustomerDocument> findCustomerById(String identity) {
		return customerRepository.findById(identity);
	}

	public Flux<CustomerDocument> findCustomersByPage(int page, int size) {
		return customerRepository.findAllByPage(PageRequest.of(page, size));
	}
}
