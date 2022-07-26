package br.com.alura.comex;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {  
		ArrayList<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
		
		Pedido primeiroPedido = pedidos.get(0);
		System.out.println("Primeiro pedido: " + primeiroPedido);
		Pedido ultimoPedido = pedidos.get(pedidos.size() -1);
		System.out.println("Último pedido: " + ultimoPedido);
		
				
		ArrayList<String> listaClientes = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			listaClientes.add(pedido.getCliente());
		}
		for (String cliente : listaClientes) {
			System.out.println(cliente);
			
		}
	}
}



