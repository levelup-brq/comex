package br.com.alura.comex.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
public class ProdutoControllerTests {
  
  @Autowired
  private MockMvc mockMvc;
  
  public List<String> criarProduto() {

    try {
      JSONObject produto = new JSONObject();
      produto.put("categoriaId",1);
      produto.put("descricao", "muito bom");
      produto.put("nome", "livro");
      produto.put("precoUnitario", 30);
      produto.put("quantidadeEmEstoque", 100);

      JSONObject segundoProduto = new JSONObject();
      segundoProduto.put("categoriaId", 2);
      segundoProduto.put("descricao", "muito bom");
      segundoProduto.put("nome", "livro 123");
      segundoProduto.put("precoUnitario", 30);
      segundoProduto.put("quantidadeEmEstoque", 100);

      return Arrays.asList(produto.toString(), segundoProduto.toString());
    
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  @Test
  public void DeveCadastrarDoisProdutos() throws URISyntaxException {

    URI uri = new URI("/api/produtos");
      
    this.criarProduto().forEach(produto -> {

      try {
        mockMvc.perform(MockMvcRequestBuilders
          .post(uri)
          .content(produto.toString())
          .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(MockMvcResultMatchers
            .content()
            .json(produto.toString()))

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

  @Order(2)
  @Test
  public void DeveTerDoisProdutosCadastradosNoBancoDeDados() throws Exception {

    URI uri = new URI("/api/produtos?page=0");

    mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray());

  }
}
