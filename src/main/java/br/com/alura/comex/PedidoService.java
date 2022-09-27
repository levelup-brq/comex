package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Pedido;

public class PedidoService {
	
	
	
	/*
	 * public List<Cliente> retornaListaNomesClientes() {
	 * 
	 * 
	 * Scanner leitorDeLinhas =
	 * ProcessadorDeCsv.processaArquivoCliente("pedidos.csv");
	 * leitorDeLinhas.nextLine(); List<Cliente> cliente = new ArrayList();
	 * 
	 * while (leitorDeLinhas.hasNextLine()) { String linha =
	 * leitorDeLinhas.nextLine(); String[] registro = linha.split(",");
	 * 
	 * String categoria = registro[0]; String produto = registro[1]; BigDecimal
	 * preco = new BigDecimal(registro[2]); int quantidade =
	 * Integer.parseInt(registro[3]); LocalDate data = LocalDate.parse(registro[4],
	 * DateTimeFormatter.ofPattern("dd/MM/yyyy")); Cliente cliente = new Cliente();
	 * cliente.setNome(registro[5]);
	 * 
	 * Pedido pedido = new Pedido(cliente, data);
	 * 
	 * pedidos.add(pedido);
	 * 
	 * } //List<Cliente> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
	 * return pedidos.stream().map(Pedido::getCliente).collect(Collectors.toList());
	 * } public Set<String> listarCategoriaDosPedidosSemDuplicacao(){
	 * 
	 * Set<String> categoriasSemDuplicacao = new HashSet<>(); List<Pedido> pedidos =
	 * ProcessadorDeCsv.processaArquivo("pedidos.csv"); pedidos.forEach(pedido ->
	 * categoriasSemDuplicacao.add(pedido.getCategoria()));
	 * //categoriasSemDuplicacao.forEach(categoria ->
	 * {System.out.println(categoria);}); return categoriasSemDuplicacao; }
	 */
	 
}
