package br.com.alura.comex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProcessaPedidos {

  List<Pedido> pedidos;

  ProcessaPedidos() {
    this.pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
  }

  public List<String> nomeDosClientes() {
    List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

    return pedidos
      .stream()
      .map(Pedido::getCliente)
      .collect(Collectors.toList());
  }

  public Set<String> categoriasSemDuplicacoes() {
    Set<String> categoriasSemDuplicacao = new HashSet<>();
    
    this.pedidos
      .stream()
      .forEach(pedido -> categoriasSemDuplicacao.add(pedido.getCategoria()));
    
    return categoriasSemDuplicacao;
  }

}
