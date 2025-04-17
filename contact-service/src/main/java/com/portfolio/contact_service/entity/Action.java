package com.portfolio.contact_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.type.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "action_type", nullable = false, unique = true)
    private String actionType;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Visitor> visitors;

    public Action(String actionType) {
        this.actionType = actionType;
    }
}