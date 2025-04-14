package com.portfolio.contact_service.repository;

import com.portfolio.contact_service.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorsRepo extends JpaRepository<Visitor, Integer> {
}
