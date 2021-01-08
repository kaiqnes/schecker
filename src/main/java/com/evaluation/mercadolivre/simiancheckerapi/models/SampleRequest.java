package com.evaluation.mercadolivre.simiancheckerapi.models;

import javax.validation.constraints.NotEmpty;

import com.evaluation.mercadolivre.simiancheckerapi.validations.SquareStructure;

@SquareStructure
public class SampleRequest {

    @NotEmpty
    private String[] dna;

    public String[] getDna() {
        return this.dna;
    }

    public SampleRequest() {

    }

    public SampleRequest(String[] dna) {
        super();
        this.dna = dna;
    }
}
