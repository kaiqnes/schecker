package com.evaluation.mercadolivre.simiancheckerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.evaluation.mercadolivre.simiancheckerapi.dao.impl.SampleDAOImpl;
import com.evaluation.mercadolivre.simiancheckerapi.service.impl.StatusServiceImpl;

@ExtendWith(SpringExtension.class)
public class StatusServiceImplTest {

	@TestConfiguration
	static class StatusServiceImplTestContextConfiguration {
		
		@Bean
		public StatusServiceImpl statusServiceImpl() {
			return new StatusServiceImpl();
		}
	}
	
	@Autowired
	private StatusServiceImpl statusService;
	
	@MockBean
	private SampleDAOImpl sampleDAO;
	
	@Test
	void shouldReturn200_gettingStatusBody() {
		ResponseEntity<?> response = statusService.getStatus();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
