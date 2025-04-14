package com.portfolio.contact_service;

import com.portfolio.contact_service.repository.ActionsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ContactServiceApplication implements CommandLineRunner {

	private final ActionsRepo actionsRepository;

	public ContactServiceApplication(ActionsRepo actionsRepository) {
		this.actionsRepository = actionsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ContactServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Fetching all visitors...");

		var actions = actionsRepository.findAll();

		if (actions.isEmpty()) {
			System.out.println("⚠️ No actions found in the database!");
		} else {
			actions.forEach(action -> {
				System.out.println(action.getId() + ": " + action.getActionType());
			});
		}

		System.out.println("✅ Database connection and fetch successful!");
	}
}
