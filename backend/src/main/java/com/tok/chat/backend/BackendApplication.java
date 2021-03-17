package com.tok.chat.backend;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.tok.chat.backend.database.message.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

		producer.sendMessageMessage(new Message("4711", "Test", "Test1", "Das ist ein TEst", new Date()));
        listener.messageLatch.await(10, TimeUnit.SECONDS);

        context.close();
	}

	@Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

	public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, Message> kafkaTemplate;


        public void sendMessageMessage(Message message) {
            kafkaTemplate.send("chat", message);
        }
    }

    public static class MessageListener {

        private CountDownLatch messageLatch = new CountDownLatch(1);

        @KafkaListener(topics = "chat", containerFactory = "kafkaListenerContainerFactory")
        public void messageListener(Message message) {
            System.out.println("Received greeting message: " + message);
            this.messageLatch.countDown();
        }

    }


}
