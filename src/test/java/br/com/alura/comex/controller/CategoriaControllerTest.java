package br.com.alura.comex.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.controller.dto.CategoriaDTO;
import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<String> initTest() {
		
		JSONObject catA = new JSONObject();
		catA.put("nome", "cat A");
		
		JSONObject catB = new JSONObject();
		catB.put("nome", "cat B");
		
		JSONObject catC = new JSONObject();
		catC.put("nome", "cat C");
		
		return Arrays.asList(
				catA.toString(),
				catB.toString(),
				catC.toString());	
		
	}
	
	@Test
	@Order(1)
	void deveCadastrarTresNovasCategorias() throws Exception {
		
		URI uri = new URI("/api/categorias"); 
		
		this.initTest().forEach(cat -> {
			
			try {
				
				mockMvc
				.perform(MockMvcRequestBuilders
							.post(uri)
							.content(cat)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.content()
						.json(cat))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(201));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	} 

	@Test
	@Order(2)
	void deveRetorna3CategoriasCadastradas() throws Exception {
		
		URI uri = new URI("/api/categorias"); 
		
		MvcResult result = mockMvc
			.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON)
			).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		List<CategoriaDTO> categorias = mapper.readValue(
				result
					.getResponse()
					.getContentAsString(),
				new TypeReference<List<CategoriaDTO>>() {});
		
		assertEquals(3, categorias.size());
		
	}

}
