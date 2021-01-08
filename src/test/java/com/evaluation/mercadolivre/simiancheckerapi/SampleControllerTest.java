package com.evaluation.mercadolivre.simiancheckerapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.evaluation.mercadolivre.simiancheckerapi.controllers.SampleController;
import com.evaluation.mercadolivre.simiancheckerapi.models.SampleRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private SampleController sampleController;

	private final String[] validDnaSample = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG" };
	private final String[] invalidDnaSample = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA" };
	private final String[] emptySample = {};

	@Test
	void ShouldReturnStatus200_postADnaSample() throws JsonProcessingException, Exception {
		mock.perform(post("/simian").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(objectMapper.writeValueAsString(new SampleRequest(validDnaSample)))).andExpect(status().isOk());
	}

	@Test
	void ShouldReturnStatus400_postAnInvalidDnaSample() throws Exception {
		mock.perform(post("/simian").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(objectMapper.writeValueAsString(new SampleRequest(invalidDnaSample))))
				.andExpect(status().isBadRequest());
	}

	@Test
	void ShouldReturnStatus400_postAEmptyDnaSample() throws JsonProcessingException, Exception {
		mock.perform(post("/simian").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(objectMapper.writeValueAsString(new SampleRequest(emptySample))))
				.andExpect(status().isBadRequest());
	}

	@Test
	void ShouldReturnStatus400_postWithoutBody() throws Exception {
		mock.perform(post("/simian").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void ShouldReturnStatus404_postOnInvalidResource() throws JsonProcessingException, Exception {
		mock.perform(post("/invalid_address").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(objectMapper.writeValueAsString(new SampleRequest(validDnaSample))))
				.andExpect(status().isNotFound());
	}
}
