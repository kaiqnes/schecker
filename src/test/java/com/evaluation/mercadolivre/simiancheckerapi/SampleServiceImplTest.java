package com.evaluation.mercadolivre.simiancheckerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.evaluation.mercadolivre.simiancheckerapi.dao.impl.SampleDAOImpl;
import com.evaluation.mercadolivre.simiancheckerapi.service.impl.SampleServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SampleServiceImplTest {

	@TestConfiguration
	static class SampleServiceImplTestContextConfiguration {

		@Bean
		public SampleServiceImpl sampleServiceImpl() {
			return new SampleServiceImpl();
		}
	}

	@Autowired
	private SampleServiceImpl sampleService;

	@MockBean
	private SampleDAOImpl sampleDAO;

	private final String[] simianSample = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCGTA", "TCACTG" };
	private final String[] humanSample = { "CTGAGA", "CTGAGC", "TATTAT", "AGAGGG", "ACCCTA", "TCACTG" };

	@Test
	void shouldReturn200_postASimianDna() {
		ResponseEntity<?> result = sampleService.verifySample(simianSample);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	void shouldReturn403_postAHumanDna() {
		ResponseEntity<?> result = sampleService.verifySample(humanSample);
		assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}
}
