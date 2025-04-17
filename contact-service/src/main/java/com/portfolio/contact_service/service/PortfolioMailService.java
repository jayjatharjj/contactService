package com.portfolio.contact_service.service;

import com.portfolio.contact_service.utils.email.EmailDetails;
import com.portfolio.contact_service.utils.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.context.Context;

@Service
public class PortfolioMailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendPortfolioViewEmail(String recipient) {
        String subject = "Thanks for visiting my portfolio!";
        Context ctx = new Context();
        String htmlBody = templateEngine.process("portfolio-feedback", ctx);
        EmailDetails emailDetails = new EmailDetails(recipient, subject, htmlBody);
        emailSender.sendPortfolioMail(emailDetails);
    }

    public void sendDownloadResumeEmail(String recipient) {
        String subject = "Thanks for downloading my resume!";
        Context ctx = new Context();
        String htmlBody = templateEngine.process("resume-feedback", ctx);
        EmailDetails emailDetails = new EmailDetails(recipient, subject, htmlBody);
        emailSender.sendPortfolioMail(emailDetails);
    }

    public void sendVisitorContactEmail(String sender, String subject, String body) {
        String updatedSubject = sender + " : " + subject;
        EmailDetails emailDetails = new EmailDetails("jayjatharjj@gmail.com", updatedSubject, body);
        emailSender.sendPortfolioMail(emailDetails);
    }
}
