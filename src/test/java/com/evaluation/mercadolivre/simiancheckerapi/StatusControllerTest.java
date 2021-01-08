package com.evaluation.mercadolivre.simiancheckerapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.evaluation.mercadolivre.simiancheckerapi.controllers.StatusController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StatusController.class)
public class StatusControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private StatusController statusController;

	@Test
	void ShouldReturnStatus200_getTheStatus() throws Exception {
		mock.perform(get("/stats")).andExpect(status().isOk());
	}

	@Test
	void ShouldReturnStatus404_getOnInvalidResoure() throws Exception {
		mock.perform(get("/invalid_address")).andExpect(status().isNotFound());
	}
}
