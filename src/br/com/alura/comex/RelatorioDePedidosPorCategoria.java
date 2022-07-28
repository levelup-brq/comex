package br.com.alura.comex;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioDePedidosPorCategoria {
  ArrayList<String> listaDeCategorias;

  RelatorioDePedidosPorCategoria(ArrayList<String> listaDeCategorias) {
    this.listaDeCategorias = listaDeCategorias;
  }

  public Map<String, Long> pedidoPorCategoria() {
    return this.listaDeCategorias.stream()
      .collect(
        Collectors
          .groupingBy(categoria -> categoria, Collectors.counting()));

  }
}
