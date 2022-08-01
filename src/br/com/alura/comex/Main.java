package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
	  List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
    
	  System.out.println("Número de pedidos: "+pedidos.size());
	  
	  // Quebra de Linha
	  System.out.println("");
	  
	  System.out.println("Primeiro pedido: "+pedidos.get(0));
	  System.out.println("Ultimo pedido: "+pedidos.get(pedidos.size() - 1));
	  
	  List<String> nomes = new ArrayList<>();
	  List<BigDecimal> precos = new ArrayList<>();
	  Set<String> categoriasSemDuplicacao = new HashSet<>();
	  Set<String> nomesOrdenados = new HashSet<>();
	  
	  // Quebra de Linha
	  System.out.println("");
	  System.out.println("Lista com nomes dos clientes");
	  
	  for(Pedido p : pedidos) {
		  
		  nomes.add(p.getCliente());
		  nomesOrdenados.add(p.getCliente());
		  System.out.println(p.getCliente());
		  
		  precos.add(p.getPreco());
	
		  categoriasSemDuplicacao.add(p.getCategoria());
	  }
	  
	  // Quebra de Linha
	  System.out.println("");
	  System.out.println("Lista ordenada de preços");
	  pedidos.stream()
      		.sorted((p1, p2) -> p1.getPreco().compareTo(p2.getPreco()))
      			.forEach(p -> System.out.println(p.getPreco()));
	  
	  
	  
	  // Quebra de Linha
	  System.out.println("");
	  System.out.println("Lista de categorias sem duplicações");
	  categoriasSemDuplicacao.stream().forEach(c -> System.out.println(c));
    
	  
	  
	  // Quebra de Linha
 	  System.out.println("");
 	  System.out.println("Lista ordenada de clientes sem duplicações");
 	  nomesOrdenados.stream()
       		.sorted((n1, n2) -> n1.compareTo(n2))
       			.forEach(n -> System.out.println(n));
 	  
 	 
 	 // Quebra de Linha
 	 System.out.println("");
 	 System.out.println("Relatório de número de pedidos por categoria");
 	 Map<String, Integer> report = pedidos.stream().collect(
 			  	Collectors.groupingBy(p -> p.getCategoria(),
 			  	Collectors.summingInt(q -> 1)));
 	  
	  
 	 report.forEach((c, q) -> System.out.println( c + " : " + q));
  }
}
