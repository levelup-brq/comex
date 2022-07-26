package br.com.alura.comex;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    ArrayList<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    System.out.println(pedidos);
  }
}
