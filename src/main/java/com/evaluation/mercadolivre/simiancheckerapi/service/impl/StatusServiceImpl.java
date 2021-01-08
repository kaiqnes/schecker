package com.evaluation.mercadolivre.simiancheckerapi.service.impl;

import com.evaluation.mercadolivre.simiancheckerapi.dao.impl.SampleDAOImpl;
import com.evaluation.mercadolivre.simiancheckerapi.models.StatusResponse;
import com.evaluation.mercadolivre.simiancheckerapi.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private SampleDAOImpl sampleDAO;

    @Override
    public ResponseEntity<?> getStatus() {
        StatusResponse status = sampleDAO.getStatus();
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
