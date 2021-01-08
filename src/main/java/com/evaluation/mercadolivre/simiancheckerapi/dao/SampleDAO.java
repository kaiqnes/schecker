package com.evaluation.mercadolivre.simiancheckerapi.dao;

import com.evaluation.mercadolivre.simiancheckerapi.models.Sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDAO extends JpaRepository<Sample, Long> {
    public Sample findByDna(String dna);

}
