package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) {

    ArrayList<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    System.out.println("Número de pedidos: " + pedidos.size());

    Pedido primeiroPedido = pedidos.get(0);
    System.out.println("Primeiro pedido: " + primeiroPedido);

    Pedido ultimoPedido = pedidos.get(pedidos.size()-1);
    System.out.println("Ultimo pedido: " + ultimoPedido);

    ArrayList<String> listaClientes = new ArrayList<>();
    for (Pedido pedido: pedidos) {
      listaClientes.add(pedido.getCliente());
      }

    for (String cliente: listaClientes) {
        System.out.println(cliente);
    }

    var comparator = new PrecoPedidoComparator();
    pedidos.sort(comparator);

    for (Pedido pedido: pedidos) {
      System.out.println(pedido.getPreco());
    }

    HashSet<String> listaCategorias = new HashSet<>();
    for (Pedido pedido: pedidos) {
      listaCategorias.add(pedido.getCategoria());
    }
    for (String categoria: listaCategorias) {
      System.out.println(categoria);
    }

    SortedSet<String> listaClientesOrdenados = new TreeSet<>(listaClientes);

    for (Pedido pedido: pedidos) {
      listaClientesOrdenados.add(pedido.getCliente());
    }

    for (String cliente: listaClientesOrdenados) {
      System.out.println(cliente);
    }

  }
}
