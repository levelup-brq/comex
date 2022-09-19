package br.com.alura.comex;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner {

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
//        inicializadorCliente();
        inicializadorCategoria();
    }
    private void inicializadorCliente() {
        salvarClientes();
        buscarTodos();
        buscaClientePorNome();
        buscaTodosPorStatus();
    }

    private void inicializadorCategoria() {
//        cadastraTresCategoriasAtivas();
//        buscaTodasCategorias();
//        alteraCategoriaParaInativa();
//        buscaTodasCategorias();
//        listaTodasAsCategoriasAtivas();
//        buscaTodasCategoriasPaginada(1);
    }

    private void buscarTodos() {
        System.out.println("Buscar todos clientes");
        clienteRepository.findAll().forEach(System.out::println);
        System.out.println("\n");
    }

    private void buscaClientePorNome() {
        System.out.println("Buscar por nome");

        List<Cliente> clientes = clienteRepository.findByNome("Palmirinha");
        clientes.forEach(System.out::println);
        System.out.println("\n");
    }

    private void buscaTodosPorStatus() {
        System.out.println("Buscar por Status");

        List<Cliente> clientes = clienteRepository.findByStatus(Status.ATIVO);
        clientes.forEach(System.out::println);
        System.out.println("\n");
    }

//    private void atualizarClienteParaSuspenso() {
//
//        Cliente cliente = clienteRepository.findById(2L).orElse(null);
//        cliente.setStatus(Status.SUSPENSO);
//        clienteRepository.save(cliente);
//    }

    public void salvarClientes() {
        System.out.println("Clientes salvos \n ");

        Cliente cliente = new Cliente();

        cliente.setNome("Frank Castle");
        cliente.setCpf("11122233344");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Contador");
        cliente.setEmail("punisher@mail.com");
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);

        cliente = new Cliente();

        cliente.setNome("Peter Parker");
        cliente.setCpf("22233344455");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Fotógrafo");
        cliente.setEmail("spiderman@mail.com");
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);

        cliente = new Cliente();
        cliente.setNome("Palmirinha");
        cliente.setCpf("33344455566");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Cozinheira");
        cliente.setEmail("palmirinha@mail.com");
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);
    }

//    private void cadastraTresCategoriasAtivas() {
//        System.out.println("Categorias cadastradas \n ");
//
//        Categoria informatica = new Categoria();
//        informatica.setNomeCategoria("MONITORES 1");
//
//        Categoria beleza = new Categoria();
//        beleza.setNomeCategoria("CREMES 1");
//
//        Categoria livros = new Categoria();
//        livros.setNomeCategoria("A TORRE NEGRA 1");
//
//        Categoria moveis = new Categoria();
//        moveis.setNomeCategoria("CADEIRAS 1");
//
//        categoriaRepository.save(informatica);
//        categoriaRepository.save(beleza);
//        categoriaRepository.save(livros);
//        categoriaRepository.save(moveis);
//    }

//    private void alteraCategoriaParaInativa() {
//        System.out.println("Status Categoria alterado \n ");
//
//        Categoria categoria = categoriaRepository.findById(2L).orElse(null);
//        categoria.setStatus(StatusCategoria.INATIVA);
//
//        categoriaRepository.save(categoria);
//
//        System.out.println("\n");
//
//    }
//
//    private void buscaTodasCategorias() {
//        System.out.println("Categorias disponíveis \n ");
//
//        categoriaRepository.findAll().forEach(System.out::println);
//
//        System.out.println("\n");
//    }
//
//    private void buscaTodasCategoriasPaginada(Integer pagina) {
//        System.out.println("Categorias paginadas \n ");
//
//        Pageable pageable = PageRequest.of(pagina, 5, Sort.unsorted());
//        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
//        System.out.println(categorias);
//        System.out.println("Pagina atual " + categorias.getNumber());
//        System.out.println("Total elemento " + categorias.getTotalElements());
//        categorias.forEach(System.out::println);
//
//        System.out.println("\n");
//    }
//

//    private void listaTodasAsCategoriasAtivas() {
//        System.out.println("Categorias ativas \n ");
//
//        List<Categoria> categorias = categoriaRepository.findByStatus(Status.ATIVO);
//
//        categorias.forEach(System.out::println);
//
//        System.out.println("\n");
//    }

}