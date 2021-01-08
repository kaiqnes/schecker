package com.evaluation.mercadolivre.simiancheckerapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.evaluation.mercadolivre.simiancheckerapi.dao.impl.SampleDAOImpl;
import com.evaluation.mercadolivre.simiancheckerapi.models.DnaOrigin;
import com.evaluation.mercadolivre.simiancheckerapi.models.StatusResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SampleDAOImplTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private SampleDAOImpl sampleDAO;

	final String[] dnaSample = { "CTGGGA", "CTTAGC", "TATTGT", "AGAGGG", "CCTCTA", "TCACTG" };

	@Test
	void shouldReturnsInjectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(sampleDAO).isNotNull();
	}

	@Test
	void shouldPersist_persistADNASample() {
		sampleDAO.persistOnSample(dnaSample, DnaOrigin.HUMAN);
	}

	@Test
	void shouldReturnNotNull_retrieveStatusOfSample() {
		assertThat(sampleDAO.getStatus()).isNotNull();
	}

	@Test
	void shouldReturnSuccess_retrieve3ValuesOnStatus() {
		String[] dna = { "CTGGGA", "CTTAGC", "TATTGT", "AGAGGG", "CCTCTA", "TCACTG" };
		sampleDAO.persistOnSample(dna, DnaOrigin.HUMAN);

		dna[4] = "CCCCTA";
		sampleDAO.persistOnSample(dna, DnaOrigin.SIMIAN);

		dna[2] = "TTTTGT";
		sampleDAO.persistOnSample(dna, DnaOrigin.SIMIAN);

		StatusResponse result = sampleDAO.getStatus();

		assertEquals(1, result.getCount_human_dna());
		assertEquals(2, result.getCount_mutant_dna());
		assertEquals(2d, result.getRatio());
	}

	@Test
	void shouldReturnSuccess_retrieveEmptyStatus() {
		StatusResponse result = sampleDAO.getStatus();

		assertEquals(0, result.getCount_human_dna());
		assertEquals(0, result.getCount_mutant_dna());
		assertEquals(0d, result.getRatio());
	}

	@Test
	void shouldReturnASingleRegister_persistDuplicatedDNASample() {
		sampleDAO.persistOnSample(dnaSample, DnaOrigin.HUMAN);
		sampleDAO.persistOnSample(dnaSample, DnaOrigin.HUMAN);

		StatusResponse result = sampleDAO.getStatus();

		assertEquals(1, result.getCount_human_dna());
	}
}
