package com.portfolio.contact_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;
}
