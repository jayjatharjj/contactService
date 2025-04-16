package com.portfolio.contact_service.controller;

import com.portfolio.contact_service.model.ResponseDTO;
import com.portfolio.contact_service.model.VisitorRequestDTO;
import com.portfolio.contact_service.service.VisitorContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class VisitorController {

    @Autowired
    VisitorContactService visitorContactService;

    @PostMapping("/api/view/portfolio")
    ResponseEntity<ResponseDTO> postPortfolioView(@RequestBody VisitorRequestDTO visitorRequestDTO) {
        return ResponseEntity.ok(visitorContactService.recordPortfolioView(visitorRequestDTO));
    }
}
