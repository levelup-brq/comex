package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    /**
     * Número de Pedidos
     */
    ArrayList<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    String numeroDePedido = String.format("Número de pedidos: %s", pedidos.size());
    System.out.println(numeroDePedido);


    /**
     * Imprima informações do primeiro e do último pedido
     */
    String primeiroPedido = String.format("Primeiro pedido: %s", pedidos.get(0));
    String ultimoPedido = String.format("Ultimo pedido: %s", pedidos.get(pedidos.size() - 1));
    System.out.println(primeiroPedido);
    System.out.println(ultimoPedido);
    
    
    /**
     * Lista com o nome dos clientes
     */
    ArrayList<String> listaDeClientes = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      listaDeClientes.add(pedido.getCliente());
    }

    for (String cliente : listaDeClientes) {
      System.out.println(cliente);
    }

    
    /**
     * Lista de preços ordenados
     */
    OrdenarPedidosPorPreço ordenarPedidosPorPreço = new OrdenarPedidosPorPreço();
    pedidos.sort(ordenarPedidosPorPreço);

    for (Pedido pedido : pedidos) {
      System.out.println(pedido.getPreco());
    }


    /**
     * Lista de Categoria sem duplicação
     */
    ArrayList<String> listaDeCategorias = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      listaDeCategorias.add(pedido.getCategoria());
    }

    ArrayList<String> categoriasSemDuplicacao = new ArrayList<>(new HashSet<>(listaDeCategorias));
    for (String categoria : categoriasSemDuplicacao) {
      System.out.println(categoria);
    }


    /**
     * Lista de clientes sem duplicações
     */
    ArrayList<String> listaDeClientesUnicos = new ArrayList<>(new HashSet<>(listaDeClientes));
    
    listaDeClientesUnicos.sort((String c1, String c2) -> {
      return c1.compareTo(c2);
    });

    for (String cliente : listaDeClientesUnicos) {
      System.out.println(cliente);
    }


    /**
     * Relatório de pedido por categoria
     */
    RelatorioDePedidosPorCategoria pedidosPorCategoria = new RelatorioDePedidosPorCategoria(listaDeCategorias);
    Map<String, Long> pedidosMap = pedidosPorCategoria.pedidoPorCategoria();

    for (Map.Entry<String, Long> pedido : pedidosMap.entrySet()) {
      String pedidoFormatado = String.format("%s: %s", pedido.getKey(), pedido.getValue());
      System.out.println(pedidoFormatado);
    }

  }
}
