package com.api.springapirest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMessage {

    private Map<String, String> message;
    private String details;
    private Date timestamp;

    public ErrorMessage(Map<String, String> message, String details, Date timestamp) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
