package br.com.alura.comex;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoService {

	public List<String> retornaListaNomesClientes() {
		List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

		return pedidos.stream().map(Pedido::getCliente).collect(Collectors.toList());
	}

}
