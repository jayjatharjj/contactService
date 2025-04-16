package com.portfolio.contact_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitorRequestDTO {

    @JsonProperty("email")
    private String email;
}
