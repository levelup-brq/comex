package br.com.alura.comex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		Set<String> categoriasSemDuplicacao = new HashSet<>();
		Set<String> nomesOrdenados = new HashSet<>();

		pedidos.stream().forEach(p -> {
			categoriasSemDuplicacao.add(p.getCategoria());
			nomesOrdenados.add(p.getCliente());
		});

		/*
		 * for (Pedido p : pedidos) { nomesOrdenados.add(p.getCliente());
		 * categoriasSemDuplicacao.add(p.getCategoria()); }
		 */

		// Quebra de Linha
		System.out.println("");
		System.out.println("Lista de categorias sem duplica��es");
		categoriasSemDuplicacao.stream().forEach(c -> System.out.println(c));

		// Quebra de Linha
		System.out.println("");
		System.out.println("Lista ordenada de clientes sem duplica��es");
		nomesOrdenados.stream().sorted((n1, n2) -> n1.compareTo(n2)).forEach(n -> System.out.println(n));

		// Quebra de Linha
		System.out.println("");
		System.out.println("Relat�rio de n�mero de pedidos por categoria");
		pedidos.stream().collect(Collectors.groupingBy(p -> p.getCategoria(), Collectors.counting()))
				.forEach((categoria, qtd) -> System.out.println(categoria + " : " + qtd));
	}
}
