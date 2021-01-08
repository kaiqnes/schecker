package com.evaluation.mercadolivre.simiancheckerapi.controllers;

import com.evaluation.mercadolivre.simiancheckerapi.service.impl.StatusServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatusController {

    @Autowired
    private StatusServiceImpl statusService;

    @GetMapping
    public ResponseEntity<?> getStatus() {
        return statusService.getStatus();
    }
}
