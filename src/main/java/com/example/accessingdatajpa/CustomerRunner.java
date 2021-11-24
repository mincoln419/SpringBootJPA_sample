package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomerRunner {
	private static final Logger log = LoggerFactory.getLogger(CustomerRunner.class);

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// sav a few customers
			repository.save(new Customer(1, "Jack", "Bauer"));
			repository.save(new Customer(2, "Chloe", "O'Brain"));
			repository.save(new Customer(3, "Kim", "Bauer"));
			repository.save(new Customer(4, "David", "Palmer"));
			repository.save(new Customer(5, "Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll()::");
			log.info("--------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------");
			repository.findByLastName("Bauer").forEach(name -> {
				log.info(name.toString());
			});
			log.info("");
		};
	}
}
