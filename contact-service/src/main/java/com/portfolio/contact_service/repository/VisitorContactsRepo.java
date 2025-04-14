package com.portfolio.contact_service.repository;

import com.portfolio.contact_service.entity.VisitorContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorContactsRepo extends JpaRepository<VisitorContact, Integer> {
}
