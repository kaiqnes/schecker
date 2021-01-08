package com.evaluation.mercadolivre.simiancheckerapi.exceptions;

import java.time.OffsetDateTime;

public class ErrorMessage {

    private OffsetDateTime timestamp;
    private Integer status;
    private String error;
    private String field;

    public ErrorMessage(String error, String field, Integer status) {
        super();
        this.error = error;
        this.field = field;
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
