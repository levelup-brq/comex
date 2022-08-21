package br.com.alura.comex;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class ProcessaPedidosTest {
  
  @Test
  void DeveRetornarUmaListaNaoVaziaDosNomeDosClientesDosPedidos() {
    ProcessaPedidos processaPedidos = new ProcessaPedidos();
    List<String> nomeDosClientes = processaPedidos.nomeDosClientes();
    assertFalse(nomeDosClientes.isEmpty());
  }

  @Test
  void deveRetornarUmaListaDeCategoriasSemDuplicacoesNaoVazia() {
    ProcessaPedidos processaPedidos = new ProcessaPedidos();
    assertEquals(processaPedidos.categoriasSemDuplicacoes().getClass(), HashSet.class);
  }

  @Test
  void metodoCategoriasSemDuplicacoesDeveRetornarUmHashSet() {
    ProcessaPedidos processaPedidos = new ProcessaPedidos();
    assertEquals(processaPedidos.categoriasSemDuplicacoes().getClass(), HashSet.class);
  }

}
