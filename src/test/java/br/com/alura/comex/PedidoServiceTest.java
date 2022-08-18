package br.com.alura.comex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class PedidoServiceTest {

	@Test
	public void deveRetornarListaNomesClientes() {
		List<String> listaDeNomes = new PedidoService().retornaListaNomesClientes();
		assertTrue(listaDeNomes.size() > 0);
	}

}
