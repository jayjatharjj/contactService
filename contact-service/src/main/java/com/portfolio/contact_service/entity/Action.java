package com.portfolio.contact_service.entity;

import com.google.type.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "actions")
public class Action {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "action_type", nullable = false)
    private String actionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Action(String actionType) {
        this.actionType = actionType;
    }
}