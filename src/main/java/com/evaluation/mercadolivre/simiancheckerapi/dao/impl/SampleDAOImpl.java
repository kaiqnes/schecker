package com.evaluation.mercadolivre.simiancheckerapi.dao.impl;

import java.time.OffsetDateTime;
import java.util.List;

import com.evaluation.mercadolivre.simiancheckerapi.dao.SampleDAO;
import com.evaluation.mercadolivre.simiancheckerapi.models.DnaOrigin;
import com.evaluation.mercadolivre.simiancheckerapi.models.Sample;
import com.evaluation.mercadolivre.simiancheckerapi.models.StatusResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleDAOImpl {

    @Autowired
    private SampleDAO sampleDAO;

    private Logger logger = LoggerFactory.getLogger(SampleDAOImpl.class);

    public void persistOnSample(final String[] dna, DnaOrigin origin) {
        Sample sample = sampleDAO.findByDna(String.join(";", dna));

        if (sample == null) {
            sampleDAO.save(new Sample(String.join(";", dna), origin, OffsetDateTime.now()));
        } else {
            logger.info("Duplicated DNA sample - It will not be persisted.");
        }
    }

    public StatusResponse getStatus() {
        List<Sample> samples = sampleDAO.findAll();

        Long countHuman = samples.stream().filter(param -> param.getOrigin().equals(DnaOrigin.HUMAN)).count();
        Long countSimian = samples.stream().filter(param -> param.getOrigin().equals(DnaOrigin.SIMIAN)).count();

        return new StatusResponse(countSimian.intValue(), countHuman.intValue());
    }

}