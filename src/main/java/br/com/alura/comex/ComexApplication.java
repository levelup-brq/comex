package br.com.alura.comex;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.model.StatusCliente;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner{
	
	private final ClienteRepository clienteRepository;
	private final CategoriaRepository categoriaRepository;
	
	

	public ComexApplication(ClienteRepository clienteRepository, CategoriaRepository categoriaRepository) {
		this.clienteRepository = clienteRepository;
		this.categoriaRepository = categoriaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// rotinaCliente();
		
		//rotinaCategoria();
	}
	
	private void rotinaCliente() {
		Cliente c = new Cliente();
		c.setCpf("111");
		c.setNome("A");
		c.setTel("+5521111");
		c.setEmail("a@a.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua A", "1", null, "A", "A", "RJ");
		
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setCpf("222");
		c.setNome("B");
		c.setTel("+5521222");
		c.setEmail("b@b.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua B", "2", null, "B", "B", "RJ");
		
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setCpf("333");
		c.setNome("C");
		c.setTel("+5521333");
		c.setEmail("c@c.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua C", "3", null, "C", "C", "SP");
		
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setCpf("444");
		c.setNome("D");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua C", "3", null, "C", "C", "SP");
		
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setCpf("555");
		c.setNome("E");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua E", "3", null, "E", "E", "DF");
		
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setCpf("666");
		c.setNome("F");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua F", "3", null, "F", "F", "RJ");
		
		clienteRepository.save(c);
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCAR TODOS CADASTRADOS");
		System.out.println("*********************************************");
		
		clienteRepository.findAll().forEach(System.out::println);
		
		c.setStatus(StatusCliente.SUSPENSO);
		clienteRepository.save(c);
		
		Cliente c2 = clienteRepository.findByNome("C");
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCA POR NOME");
		System.out.println("*********************************************");
		
		System.out.println(c2);
		
		System.out.println("*********************************************\n");
		
		
		List<Cliente> clientesAtivos = clienteRepository.findByStatus(StatusCliente.ATIVO);
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCA POR STATUS ATIVO");
		System.out.println("*********************************************");
		
		clientesAtivos.forEach(System.out::println);
		
		System.out.println("*********************************************\n");
		
		List<Cliente> clientesSuspensos = clienteRepository.findByStatus(StatusCliente.SUSPENSO);
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCA POR STATUS SUSPENSOS");
		System.out.println("*********************************************");
		
		clientesSuspensos.forEach(System.out::println);
		
		System.out.println("*********************************************\n");
		/*
		System.out.println("\n*********************************************");
		System.out.println("RELATORIO CLIENTES POR ESTADO");
		System.out.println("*********************************************");
		dao.relatorioClientesPorEstadoVO().forEach(System.out::println);
		*/
	}
	
	private void rotinaCategoria() {
		
		Categoria c = new Categoria();
		c.setNome("A");
		
		categoriaRepository.save(c);
		
		c = new Categoria();
		c.setNome("B");
		
		categoriaRepository.save(c);
		
		c = new Categoria();
		c.setNome("C");

		
		categoriaRepository.save(c);
		
		c.setStatus(StatusCategoria.INATIVA);
		categoriaRepository.save(c);
		
		List<Categoria> categoriasAtivas = categoriaRepository.findByStatus(StatusCategoria.ATIVA);
		
		System.out.println("\n*********************************************");
		System.out.println("CATEGORIAS ATIVAS");
		System.out.println("*********************************************");
		
		categoriasAtivas.forEach(System.out::println);
		
		System.out.println("*********************************************\n");
	}

}
