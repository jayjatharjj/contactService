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
@Table(name = "visitor_contacts")
public class VisitorContact {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "visitor_id", nullable = false)
    private int visitorId;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "created_at")
    private DateTime createdAt;

    public VisitorContact(int visitorId, String subject, String body) {
        this.visitorId = visitorId;
        this.subject = subject;
        this.body = body;
    }
}
