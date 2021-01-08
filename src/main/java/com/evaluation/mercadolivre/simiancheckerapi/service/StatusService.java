package com.evaluation.mercadolivre.simiancheckerapi.service;

import org.springframework.http.ResponseEntity;

public interface StatusService {
    public ResponseEntity<?> getStatus();
}
