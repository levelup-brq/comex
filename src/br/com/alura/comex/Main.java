package br.com.alura.comex;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

    List<String> listaClientes = new ArrayList<>();

    Set<String> conjuntoCategorias = new HashSet<>();
    for (Pedido pedido : pedidos) {
      conjuntoCategorias.add(pedido.getCategoria());
    }
    for (String categoria : conjuntoCategorias) {
      System.out.println(categoria);
    }

    System.out.println("\n");

    Map<String, Long> numeroPedidosPorCategoria = pedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria, Collectors.counting()));

    for (Map.Entry<String, Long> pedido : numeroPedidosPorCategoria.entrySet()) {
      System.out.println(pedido.getKey() + ": " + pedido.getValue());
    }

    System.out.println("\n");

    SortedSet<String> listaClientesOrdenados = new TreeSet<>(listaClientes);
    for (Pedido pedido : pedidos) {
      listaClientesOrdenados.add(pedido.getCliente());
    }
    for (String cliente : listaClientesOrdenados) {
      System.out.println(cliente);
    }
  }
}
