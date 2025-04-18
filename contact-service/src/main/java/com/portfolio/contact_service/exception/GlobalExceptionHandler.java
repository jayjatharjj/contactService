package com.portfolio.contact_service.exception;

import com.portfolio.contact_service.model.ResponseDTO;
import com.portfolio.contact_service.service.PortfolioMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Logger log;

    @Autowired
    private PortfolioMailService portfolioMailService;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleRuntimeExceptions(RuntimeException ex) {
        String error = ex.getLocalizedMessage();
        log.info(error);
        portfolioMailService.sendErrorEmail("Runtime exception occurred", error);
        return ResponseEntity.ok(new ResponseDTO("error", "Something went wrong"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleAllExceptions(Exception ex) {
        String error = ex.getLocalizedMessage();
        log.info(error);
        portfolioMailService.sendErrorEmail("Exception occurred", error);
        return ResponseEntity.ok(new ResponseDTO("error", "Something went wrong"));
    }
}
