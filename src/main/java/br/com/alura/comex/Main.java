package br.com.alura.comex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	
	public static void main(String[] args)  {  
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		//List<Pedido> pedidos = ProcessadorDeXML.processaArquivoXML("pedidos.xml");
		
		
		System.out.println(pedidos);
		
		
		
		//Exercício 1: Lista de categorias sem duplicações
		//Set<String> categoriasSemDuplicacao = new HashSet<>(); 
		//pedidos.forEach(pedido -> categoriasSemDuplicacao.add(pedido.getCategoria()));
		//categoriasSemDuplicacao.forEach(categoria -> {System.out.println(categoria);});
			
		//Exercício 2
		
//		Map<String, Integer> numeroPedidosPorCategoria = new HashMap<>();
//		for (Pedido pedido : pedidos) {
//			String categoria = pedido.getCategoria();
//			
//			Integer quantidade = numeroPedidosPorCategoria.get(categoria);
//			if(quantidade != null) {
//				numeroPedidosPorCategoria.put(categoria, quantidade + 1);
//			}else {
//				numeroPedidosPorCategoria.put(categoria, 1);
//			}
//		}
//		for (String categoria : numeroPedidosPorCategoria.keySet()) {
//			Integer quantidadePorCategoria = numeroPedidosPorCategoria.get(categoria);
//			System.out.println(categoria +": "+ quantidadePorCategoria);
//		}
		
		//Exemplo do Douglas >
		//pedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria, Collectors.counting())));
		
					
		//Exercício 3: Lista ordenada de clientes sem duplicações
		//TreeSet<String> clientesSemDuplicacaoOrdenado = new TreeSet<>(new NomeComparator());
		//pedidos.forEach(pedido -> clientesSemDuplicacaoOrdenado.add(pedido.getCliente()));
		//clientesSemDuplicacaoOrdenado.forEach(cliente -> {System.out.println(cliente);});
		
		//Exemplo Exercício 3 >
		//pedidos.stream().map(Pedido::getCliente).collect(Collectors.toCollection(TreeSet::new)).forEach(System.out::println);
	}
}



