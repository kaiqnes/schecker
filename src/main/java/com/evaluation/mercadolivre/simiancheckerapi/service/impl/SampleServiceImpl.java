package com.evaluation.mercadolivre.simiancheckerapi.service.impl;

import com.evaluation.mercadolivre.simiancheckerapi.dao.impl.SampleDAOImpl;
import com.evaluation.mercadolivre.simiancheckerapi.models.DnaOrigin;
import com.evaluation.mercadolivre.simiancheckerapi.service.SampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDAOImpl sampleDAO;

    @Override
    public ResponseEntity<?> verifySample(final String[] dna) {
        if (isSimian(dna)) {
            sampleDAO.persistOnSample(dna, DnaOrigin.SIMIAN);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        sampleDAO.persistOnSample(dna, DnaOrigin.HUMAN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private boolean isSimian(String[] dna) {
        final char[][] matrix = new char[dna.length][dna[0].length()];
        final int LIMIT = matrix.length;
        char base;

        for (int line = 0; line < dna.length; line++) { // Populating the matrix
            for (int column = 0; column < dna[0].length(); column++) {
                base = dna[line].charAt(column);

                if ((column < LIMIT - 3)) { // East
                    if (base == dna[line].charAt(column + 1) && base == dna[line].charAt(column + 2)
                            && base == dna[line].charAt(column + 3)) {
                        return true;
                    }
                }

                if ((line < LIMIT - 3)) { // South
                    if (base == dna[line + 1].charAt(column) && base == dna[line + 2].charAt(column)
                            && base == dna[line + 3].charAt(column)) {
                        return true;
                    }
                }

                if ((line < LIMIT - 3 && column < LIMIT - 3)) { // SouthEast
                    if (base == dna[line + 1].charAt(column + 1) && base == dna[line + 2].charAt(column + 2)
                            && base == dna[line + 3].charAt(column + 3)) {
                        return true;
                    }
                }

                if (line < LIMIT - 3 && column > 2) { // SouthWest
                    if (base == dna[line + 1].charAt(column - 1) && base == dna[line + 2].charAt(column - 2)
                            && base == dna[line + 3].charAt(column - 3)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
