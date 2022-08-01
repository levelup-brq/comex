package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    /**
     * Número de Pedidos
     */
    List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    String numeroDePedido = String.format("Número de pedidos: %s", pedidos.size());
    System.out.println(numeroDePedido);


    /**
     * Imprima informações do primeiro e do último pedido
     */
    String primeiroPedido = String.format("Primeiro pedido: %s", pedidos.get(0));
    String ultimoPedido = String.format("Ultimo pedido: %s", pedidos.get(pedidos.size() - 1));
    //System.out.println(primeiroPedido);
    System.out.println(ultimoPedido);

    System.out.printf("%s", pedidos.get(0));
    
    
    /**
     * Lista com o nome dos clientes
     */
    List<String> listaDeClientes = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      listaDeClientes.add(pedido.getCliente());
    }

    for (String cliente : listaDeClientes) {
      System.out.println(cliente);
    }

    
    /**
     * Lista de preços ordenados
     */
    OrdenarPedidosPorPreco ordenarPedidosPorPreco = new OrdenarPedidosPorPreco();
    pedidos.sort(ordenarPedidosPorPreco);

    for (Pedido pedido : pedidos) {
      System.out.println(pedido.getPreco());
    }


    /**
     * Lista de Categoria sem duplicação
     */
    Set<String> categoriasSemDuplicacao = new HashSet<>();

    for (Pedido pedido : pedidos) {
      categoriasSemDuplicacao.add(pedido.getCategoria());
    }

    for (String categoria : categoriasSemDuplicacao) {
      System.out.println(categoria);
    }


    /**
     * Lista de clientes sem duplicações
     */
    List<String> listaDeClientesUnicos = new ArrayList<>(new HashSet<>(listaDeClientes));
    
    listaDeClientesUnicos.sort((String c1, String c2) -> {
      return c1.compareTo(c2);
    });

    for (String cliente : listaDeClientesUnicos) {
      System.out.println(cliente);
    }


    /**
     * Relatório de pedido por categoria
     * 
     * */

    List<String> categorias = new ArrayList<>();

    for (Pedido pedido : pedidos) {
      categorias.add(pedido.getCategoria());
    }
     
    RelatorioDePedidosPorCategoria pedidosPorCategoria = new RelatorioDePedidosPorCategoria(categorias);
    Map<String, Long> pedidosMap = pedidosPorCategoria.pedidoPorCategoria();

    for (Map.Entry<String, Long> pedido : pedidosMap.entrySet()) {
      String pedidoFormatado = String.format("%s: %s", pedido.getKey(), pedido.getValue());
      System.out.println(pedidoFormatado);
    }
    

  }
}
