package com.portfolio.contact_service.repository;

import com.portfolio.contact_service.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionsRepo extends JpaRepository<Action, Integer> {

    Optional<Action> findByActionType(String actionType);
}
