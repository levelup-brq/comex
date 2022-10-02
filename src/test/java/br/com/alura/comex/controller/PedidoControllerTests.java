package br.com.alura.comex.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoControllerTests {
  
  @Autowired
  private MockMvc mockMvc;
  
  public List<JSONObject> criarPedido() {
    
    try {
      JSONObject primeiroPedido = new JSONObject();

      JSONObject primeiroItemDePedido = new JSONObject();
      primeiroItemDePedido.put("quantidade", 10);
      primeiroItemDePedido.put("produtoId", 1);

      JSONArray itens = new JSONArray();
      itens.put(primeiroItemDePedido);
      
      primeiroPedido.put("clienteId", 1);
      primeiroPedido.put("itensDoPedido", itens);


      JSONObject segundoPedido = new JSONObject();
      JSONObject itemDoSegundoPedido = new JSONObject();

      itemDoSegundoPedido.put("quantidade", 10);
      itemDoSegundoPedido.put("produtoId", 2);

      JSONArray itens2 = new JSONArray();
      itens2.put(itemDoSegundoPedido);

      segundoPedido.put("clienteId", 1);
      segundoPedido.put("itensDoPedido", itens2);

      System.out.println(">>>>"+ itens);
      System.out.println(">>>>"+ itens2);
      

      return Arrays.asList(primeiroPedido, segundoPedido);
      
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  @Order(1)
  @Test
  public void deveCadastrarDoisPedidos() throws Exception {

    URI uri = new URI("/api/pedidos");

    this.criarPedido().forEach(pedido -> {
      try {
        mockMvc.perform(MockMvcRequestBuilders
          .post(uri)
          .content(pedido.toString())
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().is(201))
          .andExpect(MockMvcResultMatchers.content().json(pedido.toString()));

      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Order(2)
  @Test
  public void DeveRetornarUmArrayDeItensDePedidos() throws Exception {

    URI uri = new URI("/api/pedidos/1");

    mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.listaDeItens").isArray());

  }


}
