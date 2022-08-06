package br.com.alura.comex;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

    /**
     * Lista de Categoria sem duplicação
     */
    Set<String> categoriasSemDuplicacao = new HashSet<>();

    for (Pedido pedido : pedidos) {
      categoriasSemDuplicacao.add(pedido.getCategoria());
    }

    for (String categoria : categoriasSemDuplicacao) {
     //System.out.println(categoria);
    }


    /**
     * Relatório de número de pedidos por categoria
     */
    Map<String, Long> categoriasSemDuplicoes = pedidos.stream()
      .collect(
        Collectors
          .groupingBy(Pedido::getCategoria, Collectors.counting()));
          

    for (Map.Entry<String, Long> pedido : categoriasSemDuplicoes.entrySet()) {
      String pedidoFormatado = String.format("%s: %s", pedido.getKey(), pedido.getValue());
      System.out.println(pedidoFormatado);
    }


  }
}
