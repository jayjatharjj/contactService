package com.portfolio.contact_service.controller;

import com.portfolio.contact_service.entity.Visitor;
import com.portfolio.contact_service.enums.ActionTypes;
import com.portfolio.contact_service.model.ContactRequestDTO;
import com.portfolio.contact_service.model.ResponseDTO;
import com.portfolio.contact_service.model.VisitorRequestDTO;
import com.portfolio.contact_service.service.VisitorContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class VisitorController {

    @Autowired
    private VisitorContactService visitorContactService;

    @GetMapping("/all")
    ResponseEntity<List<Visitor>> getPortfolioVisitors() {
        return ResponseEntity.ok(visitorContactService.getVisitors("all"));
    }

    @GetMapping("/view")
    ResponseEntity<List<Visitor>> getPortfolioView() {
        return ResponseEntity.ok(visitorContactService.getVisitors(ActionTypes.VIEW_PORTFOLIO.getValue()));
    }

    @PostMapping("/view")
    ResponseEntity<ResponseDTO> postPortfolioView(@RequestBody VisitorRequestDTO visitorRequestDTO) {
        return ResponseEntity.ok(visitorContactService.recordPortfolioView(visitorRequestDTO));
    }

    @GetMapping("/download")
    ResponseEntity<List<Visitor>> getResumeDownload() {
        return ResponseEntity.ok(visitorContactService.getVisitors(ActionTypes.DOWNLOAD_RESUME.getValue()));
    }

    @PostMapping("/download")
    ResponseEntity<ResponseDTO> postResumeDownload(@RequestBody VisitorRequestDTO visitorRequestDTO) {
        return ResponseEntity.ok(visitorContactService.recordDownloadResume(visitorRequestDTO));
    }

    @GetMapping("/contact")
    ResponseEntity<List<Visitor>> getVisitorContact() {
        return ResponseEntity.ok(visitorContactService.getVisitors(ActionTypes.EMAIL_CONTACT.getValue()));
    }

    @PostMapping("/contact")
    ResponseEntity<ResponseDTO> postVisitorContact(@RequestBody ContactRequestDTO contactRequestDTO) {
        return ResponseEntity.ok(visitorContactService.handleVisitorContact(contactRequestDTO));
    }
}
