package com.tok.chat.backend;

import java.util.Date;

import com.tok.chat.backend.database.message.Message;
import com.tok.chat.backend.kafka.KafkaProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        KafkaProducer producer = context.getBean(KafkaProducer.class);
		producer.emit(new Message("4711", "Test", "Test1", "Das ist ein TEst", new Date()));
        
	}


}
