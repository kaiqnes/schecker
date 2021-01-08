package com.evaluation.mercadolivre.simiancheckerapi.models;

import java.text.DecimalFormat;

public class StatusResponse {
    private int count_mutant_dna;
    private int count_human_dna;
    private double ratio;

    public StatusResponse(int count_mutant, int count_human) {
        super();
        this.count_mutant_dna = count_mutant;
        this.count_human_dna = count_human;
        this.ratio = calculateRatio();
    }

    private double calculateRatio() {
        Double result = 0d;
        DecimalFormat df = new DecimalFormat("#.##");

        try {
            result = (double) this.count_mutant_dna / this.count_human_dna;

            if (result == Double.NEGATIVE_INFINITY || result == Double.POSITIVE_INFINITY) {
                result = (double) this.count_mutant_dna;
            }

            if (result.isNaN()) {
                result = 0d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Double.parseDouble(df.format(result).replace(",", ""));
    }

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public double getRatio() {
        return ratio;
    }
}
