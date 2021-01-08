package com.evaluation.mercadolivre.simiancheckerapi.controllers;

import javax.validation.Valid;

import com.evaluation.mercadolivre.simiancheckerapi.models.SampleRequest;
import com.evaluation.mercadolivre.simiancheckerapi.service.impl.SampleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simian")
public class SampleController {

    @Autowired
    private SampleServiceImpl sampleService;

    @PostMapping
    public ResponseEntity<?> isSimian(@Valid @RequestBody SampleRequest sample) {
        return sampleService.verifySample(sample.getDna());
    }
}
