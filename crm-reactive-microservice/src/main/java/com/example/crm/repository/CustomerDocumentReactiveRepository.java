package com.example.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerDocumentReactiveRepository extends ReactiveMongoRepository<CustomerDocument, String> {
	Flux<CustomerDocument> findAllByBirthYear(int birthYear, Pageable page);
	@Query("{}")
	Flux<CustomerDocument> findAllByPage(Pageable page);
	Mono<CustomerDocument> findOneByEmail(String email);
}
