package com.swagger.practice.demo.application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.swagger.practice.demo.application.configuration.ApiConfiguration;
import com.swagger.practice.demo.application.repositories.ContactRepository;
import com.swagger.practice.demo.application.repositories.TutorialRepository;

@WebMvcTest
@Import(ApiConfiguration.class)
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	@Qualifier("tutorials")
	private ContactRepository repository;
	@MockBean
	@Qualifier("contacts")
	private TutorialRepository tutorialRepository;

	@Test
	public void swaggerJsonExists() throws Exception {
		String contentAsString = mockMvc
				.perform(MockMvcRequestBuilders.get("/v3/api-docs")
						.accept(MediaType.APPLICATION_JSON)
						.param("api-turorials", "tutorial-controller"))
				.andExpect(status().isOk())
				.andReturn().getResponse()
				.getContentAsString();
		try (Writer writer = new FileWriter(new File("target/generated-sources/openApi.json"))) {
			IOUtils.write(contentAsString, writer);
		}
	}
}
