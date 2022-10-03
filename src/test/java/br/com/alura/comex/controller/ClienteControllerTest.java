package br.com.alura.comex.controller;

import java.net.URI;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void test() throws Exception {
		URI uri = new URI("/api/clientes");
		
		String json = "{\n"
				+ "    \"cpf\":\"123456789\",\n"
				+ "	\"nome\":\"Teste 999\",\n"
				+ "	\"tel\":\"99999\",\n"
				+ "	\"email\":\"999@teste.teste\",\n"
				+ "	\"profissao\":\"Tester\",\n"
				+ "    \"rua\":\"Rua 3\",\n"
				+ "	\"numero\":\"348\",\n"
				+ "	\"complemento\":\"\",\n"
				+ "	\"bairro\":\"Testeiros\",\n"
				+ "    \"cidade\":\"Testadora\",\n"
				+ "	\"estado\":\"TDD\"\n"
				+ "}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status().is(201));
	}

}
