package com.example.kafka.consumer.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<String> messages = new ArrayList<>();

	User testUser = null;

	@GetMapping("/consumeStringMessage")
	public List<String> consumeMessage() {
		return messages;
	}

	@KafkaListener(groupId = "ruthvik-1", topics = "ruthvik", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;
	}

	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		return testUser;
	}

	@KafkaListener(groupId = "ruthvik-2", topics = "ruthvik", containerFactory = "userKafkaListenerContainerFactory")
	public User getMsgFromTopic(User user) {
		testUser = user;
		return testUser;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
