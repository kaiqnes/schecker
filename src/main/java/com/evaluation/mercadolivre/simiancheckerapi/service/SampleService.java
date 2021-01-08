package com.evaluation.mercadolivre.simiancheckerapi.service;

import org.springframework.http.ResponseEntity;

public interface SampleService {
    public ResponseEntity<?> verifySample(String[] dna);
}
