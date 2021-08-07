package com.envision.demo;
import com.envision.demo.model.Investment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.envision.demo.repository.InvestmentJdbcRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InvestmentJdbcRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Investment id 10001 -> {}", repository.findById(10001L));

		logger.info("All users 1 -> {}", repository.findAll());
		
		logger.info("Inserting -> {}", repository.insert(new Investment(10010L, "PGO", "PGO001")));

		logger.info("Update 10001 -> {}", repository.update(new Investment(10001L, "PFIGO", "PFIGO1")));

		repository.deleteById(10002L);
		
		logger.info("All users 2 -> {}", repository.findAll());

	}
}
