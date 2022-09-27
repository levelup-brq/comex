package br.com.alura.comex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.Pedido;

public class PedidoService {
	public List<String> retornaListaNomesClientes() {
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		return pedidos.stream().map(Pedido::getCliente).collect(Collectors.toList());
	}
	public Set<String> listarCategoriaDosPedidosSemDuplicacao(){
		
		Set<String> categoriasSemDuplicacao = new HashSet<>(); 
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		pedidos.forEach(pedido -> categoriasSemDuplicacao.add(pedido.getCategoria()));
		//categoriasSemDuplicacao.forEach(categoria -> {System.out.println(categoria);});
		return categoriasSemDuplicacao;
	}
}
