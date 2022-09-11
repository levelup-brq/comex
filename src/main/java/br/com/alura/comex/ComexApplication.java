package br.com.alura.comex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ClienteService;
import br.com.alura.comex.service.PedidoService;
import br.com.alura.comex.service.ProdutoService;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner {

	private final ClienteService clienteService;
	private final CategoriaService categoriaService;
	private final ProdutoService produtoService;
	private final PedidoService pedidoService;


	public ComexApplication(ClienteService clienteService, 
		CategoriaService categoriaService,
		ProdutoService produtoService,
		PedidoService pedidoService) {
		this.clienteService = clienteService;
		this.categoriaService = categoriaService;
		this.produtoService = produtoService;
		this.pedidoService = pedidoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//this.clientes();
		//this.categorias();
		// this.produtos();
		this.pedidos();
	}

	public void clientes() {
		this.clienteService.criar();
		// this.clienteService.atualizarClienteParaSuspenso();
		// this.clienteService.buscarClientePorNome();
		//this.clienteService.buscarClientePorStatusAtivo();
		//this.clienteService.buscarClientePorStatus();
	}

	public void categorias() {
		this.categoriaService.criar();
		//this.categoriaService.atualizarCategoriaParaInativa();
		//this.categoriaService.buscarTodasCategoriasAtivas();
		//this.categoriaService.buscarTodasCategorias();
	}

	public void produtos() {
		this.produtoService.criar();
		// this.produtoService.buscarProdutosIndisponiveis();
	}

	public void pedidos() {
		//this.pedidoService.criar();
		//this.pedidoService.buscarTodosPedidosDoCliente();
		this.pedidoService.buscarClientesMaisLucrativos();
	}

}
