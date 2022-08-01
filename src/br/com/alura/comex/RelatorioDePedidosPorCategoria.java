package br.com.alura.comex;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioDePedidosPorCategoria {
  private Collection<String> categorias;

  RelatorioDePedidosPorCategoria(Collection<String> categorias) {
    this.categorias = categorias;
  }

  public Map<String, Long> pedidoPorCategoria() {
    return this.categorias.stream()
      .collect(
        Collectors
          .groupingBy(categoria -> categoria, Collectors.counting()));

  }
}
