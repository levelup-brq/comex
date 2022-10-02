package br.com.alura.comex.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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

import br.com.alura.comex.controller.dto.ClientesDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientesControllerTests {

  @Autowired
  private MockMvc mockMvc;

  public List<String> criarCliente() throws JSONException {
    JSONObject cliente = new JSONObject();

    cliente.put("nome", "cliente");
    cliente.put("cpf", "12345678900");
    cliente.put("telefone", "11909098787");
    cliente.put("email", "cliente@outlook.com");
    cliente.put("profissao", "Developer");
    cliente.put("numero", "012");
    cliente.put("bairro", "Sao Paulo");
    cliente.put("rua", "Rua 123");
    cliente.put("cidade", "Recife");
    cliente.put("estado", "PB");
    cliente.put("complemento", "Condominio 4");
    cliente.put("usuarioId", 1);

    JSONObject segundoCliente = new JSONObject();

    segundoCliente.put("nome", "segundo cliente");
    segundoCliente.put("cpf", "12345678900");
    segundoCliente.put("telefone", "11909098787");
    segundoCliente.put("email", "cliente@outlook.com");
    segundoCliente.put("profissao", "Developer");
    segundoCliente.put("numero", "012");
    segundoCliente.put("bairro", "Sao Paulo");
    segundoCliente.put("rua", "Rua 123");
    segundoCliente.put("cidade", "Recife");
    segundoCliente.put("estado", "PB");
    segundoCliente.put("complemento", "Condominio 4");
    segundoCliente.put("usuarioId", 1);

    return Arrays.asList(cliente.toString(), segundoCliente.toString());

  }

  @Order(1)
  @Test
  public void DeveCadastrarDoisClienteNoBancoDeDados() throws Exception {
    
    URI uri = new URI("/api/clientes");

    this.criarCliente().forEach(cliente -> {

      try {
        mockMvc.perform(MockMvcRequestBuilders
          .post(uri)
          .content(cliente.toString())
          .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(MockMvcResultMatchers
            .content()
            .json(cliente.toString()))

          .andExpect(MockMvcResultMatchers
            .status()
            .is(200))

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
  public void deveConterMaisQueUmClienteCadastrado() throws Exception {

    URI uri = new URI("/api/clientes");

    MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
      
    ObjectMapper mapper = new ObjectMapper();
    List<ClientesDTO> clientes = mapper.readValue(
      resultado
        .getResponse()
        .getContentAsString(), 
      new TypeReference<List<ClientesDTO>>() {});

    assertTrue(clientes.size() > 1);

  }

}
