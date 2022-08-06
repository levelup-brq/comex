package br.com.alura.comex;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     System.out.println(categoria);
    }

  }
}
