package com.addressbookservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class AddressBookServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication
				.run(AddressBookServiceApplication.class, args);

		log.info("AddressBook app started in  {} Environment", context.getEnvironment().getProperty("environment"));
	}
}
