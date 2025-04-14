package com.portfolio.contact_service.entity;

import com.google.type.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "visitors")
public class Visitor {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "action_id", nullable = false)
    private int actionId;

    @Column(name = "created_at")
    private DateTime createdAt;

    public Visitor(String email, int actionId) {
        this.email = email;
        this.actionId = actionId;
    }
}
