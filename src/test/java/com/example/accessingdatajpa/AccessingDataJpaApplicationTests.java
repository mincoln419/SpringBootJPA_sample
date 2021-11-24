package com.example.accessingdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJpaTest
class AccessingDataJpaApplicationTests {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@Test
	void contextLoads() throws SQLException {
		try (Connection connection = datasource.getConnection()){
			DatabaseMetaData metaData;
			metaData = connection.getMetaData();
			System.out.println(metaData.getURL());
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getUserName());
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void di() throws SQLException {
		Customer customer = new Customer();
		customer.setId(999999L);
		customer.setFirstName("Michale");
		customer.setLastName("Booblele");
		
		Customer newCustomer = customerRepository.save(customer);
		assertThat(newCustomer).isNotNull();
		assertThat(customerRepository.findById(999999L).getFirstName().equals("Michale"));
	}

}
