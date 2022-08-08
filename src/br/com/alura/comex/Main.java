package br.com.alura.comex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  }
}
