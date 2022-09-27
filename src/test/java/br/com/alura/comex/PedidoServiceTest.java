package br.com.alura.comex;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class PedidoServiceTest {

	@Test
	public void deveRetornarListaNomesClientes() {
		List<String> listaDeNomes = new PedidoService().retornaListaNomesClientes();
		assertTrue(listaDeNomes.size() > 0);
	}
	@Test
	public void listaDeCategoriasDosPedidosSemRepedicaoTest() {
		PedidoList pedidos = new PedidoList();
		Set<String> categoriasSemDuplicacao = new PedidoService().listarCategoriaDosPedidosSemDuplicacao(); 
        
		assertEquals(5 , categoriasSemDuplicacao.size());
	}
}
