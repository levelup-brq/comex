package br.com.alura.comex;

import java.util.Comparator;

import br.com.alura.comex.modelo.Pedido;

public class PrecoPedidoComparator implements Comparator<Pedido>{

	@Override
	public int compare(Pedido p1, Pedido p2) {
		return p1.getPreco().compareTo(p2.getPreco());
	}

}
