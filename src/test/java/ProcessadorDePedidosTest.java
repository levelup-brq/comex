import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import br.com.alura.comex.ProcessadorDePedidos;
import org.junit.jupiter.api.Test;

class ProcessadorDePedidosTest {

	@Test
	public void testarNomesClientes() {
		List<String> listaNomes = new ProcessadorDePedidos().nomesClientes();
		assertTrue(listaNomes.size() > 0);
	}

}