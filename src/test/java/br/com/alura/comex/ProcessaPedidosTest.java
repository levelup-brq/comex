package br.com.alura.comex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class ProcessaPedidosTest {
  
  @Test
  void DeveRetornarUmaListaNaoVaziaDosNomeDosClientesDosPedidos() {
    ProcessaPedidos processaPedidos = new ProcessaPedidos();
    List<String> nomeDosClientes = processaPedidos.nomeDosClientes();
    assertFalse(nomeDosClientes.isEmpty());
  }

}
