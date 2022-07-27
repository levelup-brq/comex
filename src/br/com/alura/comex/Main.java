package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) {
    ArrayList<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    String numeroDePedido = String.format("Número de pedidos: %s", pedidos.size());
    System.out.println(numeroDePedido);

    String primeiroPedido = String.format("Primeiro pedido: %s", pedidos.get(0));
    String ultimoPedido = String.format("Ultimo pedido: %s", pedidos.get(pedidos.size() - 1));
    System.out.println(primeiroPedido);
    System.out.println(ultimoPedido);

    ArrayList<String> listaDeClientes = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      listaDeClientes.add(pedido.getCliente());
    }

    for (String cliente : listaDeClientes) {
      System.out.println(cliente);
    }

    OrdenarPedidosPorPreço ordenarPedidosPorPreço = new OrdenarPedidosPorPreço();
    pedidos.sort(ordenarPedidosPorPreço);

    for (Pedido pedido : pedidos) {
      System.out.println(pedido.getPreco());
    }


    ArrayList<String> listaDeCategorias = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      listaDeCategorias.add(pedido.getCategoria());
    }


    ArrayList<String> categoriasSemDuplicacao = new ArrayList<>(new HashSet<>(listaDeCategorias));
    for (String categoria : categoriasSemDuplicacao) {
      System.out.println(categoria);
    }
    

    ArrayList<String> listaDeClientesUnicos = new ArrayList<>(new HashSet<>(listaDeClientes));
    System.out.println(listaDeClientesUnicos);
    
    listaDeClientesUnicos.sort((String c1, String c2) -> {
      return c1.compareTo(c2);
    });

    System.out.println(listaDeClientesUnicos);
  }
}
