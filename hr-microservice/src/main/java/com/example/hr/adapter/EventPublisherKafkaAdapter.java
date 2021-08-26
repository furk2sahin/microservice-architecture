package com.example.hr.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.dto.EmployeeKafkaEvent;
import com.example.hr.events.EmployeeEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher{

	private KafkaTemplate<String, String> kafkaTemplate;
	private ModelMapper mapper;
	private ObjectMapper objectMapper;
	
	@Autowired
	public EventPublisherKafkaAdapter(
			KafkaTemplate<String, String> kafkaTemplate, 
			ModelMapper mapper,
			ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.mapper = mapper;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publish(EmployeeEvent event) {
		var employeeKafkaEvent = mapper.map(event, EmployeeKafkaEvent.class);
		try {
			var jsonDocument = objectMapper.writeValueAsString(employeeKafkaEvent);
			kafkaTemplate.send("hr", jsonDocument);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.err.println("Error:" + e.getMessage());
		}
		
	}
}
