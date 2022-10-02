package br.com.alura.comex.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriasControllerTests {

  @Autowired
  private MockMvc mockMvc;

  public List<String> criarCategoria() throws JSONException {
    JSONObject categoria = new JSONObject();
    categoria.put("nome", "Jogos");

    JSONObject segundaCategoria = new JSONObject();
    segundaCategoria.put("nome", "Moveis");

    return Arrays.asList(categoria.toString(), segundaCategoria.toString());
  }
  
  @Test
  @Order(1)
  public void deveCadastrarDuasCategorias() throws Exception {
    URI uri = new URI("/api/categorias");

    this.criarCategoria().forEach(categoria -> {
      
      try {
        mockMvc.perform(MockMvcRequestBuilders
          .post(uri)
          .content(categoria.toString())
          .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(MockMvcResultMatchers
            .content()
            .json(categoria.toString()))
  
          .andExpect(MockMvcResultMatchers
            .status()
            .is(201))
  
          .andExpect(MockMvcResultMatchers
            .header()
            .exists("Content-Type"));
      } catch (Exception e) {
        e.printStackTrace();
      }

    });

  }

  @Test
  @Order(2)
  public void deveConterMaisQueUmaCategoriaCadastrada() throws Exception {
    URI uri = new URI("/api/categorias");

    MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
      
    ObjectMapper mapper = new ObjectMapper();
    List<CategoriaDTO> categorias = mapper.readValue(
      resultado
        .getResponse()
        .getContentAsString(), 
      new TypeReference<List<CategoriaDTO>>() {});

    assertTrue(categorias.size() > 1);
  }
}
