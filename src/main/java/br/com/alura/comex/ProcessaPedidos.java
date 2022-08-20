package br.com.alura.comex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessaPedidos {

  public List<String> nomeDosClientes() {
    List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

    return pedidos
      .stream()
      .map(Pedido::getCliente)
      .collect(Collectors.toList());
  }

}
