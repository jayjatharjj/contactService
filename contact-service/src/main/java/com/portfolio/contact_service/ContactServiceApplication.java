package com.portfolio.contact_service;

import com.portfolio.contact_service.entity.Action;
import com.portfolio.contact_service.enums.ActionTypes;
import com.portfolio.contact_service.repository.ActionsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

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
		System.out.println("Adding default actions...");

		List<String> defaultActions = Arrays.stream(ActionTypes.values()).map(ActionTypes::getValue).toList();
		List<Action> actions = defaultActions.stream().map(Action::new).toList();
		actionsRepository.saveAll(actions);

		var savedActions = actionsRepository.findAll();

		if (savedActions.isEmpty()) {
			System.out.println("⚠️ No actions found in the database!");
		} else {
			savedActions.forEach(action -> {
				System.out.println(action.getId() + "| " + action.getActionType() + "| " + action.getCreatedAt());
			});
		}

		System.out.println("✅ Database connection and fetch successful!");
	}
}
