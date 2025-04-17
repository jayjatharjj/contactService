package com.portfolio.contact_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.type.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "visitor_contacts")
public class VisitorContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id", nullable = false)
    @JsonBackReference
    private Visitor visitor;

    @Column(nullable = false)
    private String subject;

    @Lob
    private String body;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public VisitorContact(Visitor visitor, String subject, String body) {
        this.visitor = visitor;
        this.subject = subject;
        this.body = body;
    }
}
