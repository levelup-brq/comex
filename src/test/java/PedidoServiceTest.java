import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import br.com.alura.comex.PedidoService;
import org.junit.jupiter.api.Test;

class PedidoServiceTest {

	@Test
	public void listaNomesClientes() {
		List<String> listaDeNomes = new PedidoService().nomesClientes();
		assertTrue(listaDeNomes.size() > 0);
	}

}