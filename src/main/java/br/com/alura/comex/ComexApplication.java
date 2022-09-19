package br.com.alura.comex;

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.modelo.*;
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
        inicializadorCliente();
//        inicializadorCategoria();
    }
    private void inicializadorCliente() {
        salvarClientes();
        buscarTodos();
        buscaClientePorNome();
        buscaTodosPorStatus();
//        alterarClienteParaSuspenso();
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

    private void alterarClienteParaSuspenso() {

        Cliente cliente = clienteRepository.findById(2L).orElse(null);
        cliente.setStatus(Status.SUSPENSO);
        clienteRepository.save(cliente);
    }

    public void salvarClientes() {
        System.out.println("Clientes salvos \n ");

        Cliente cliente1 = new Cliente();

        cliente1.setNome("Frank Castle");
        cliente1.setCpf("11122233344");
        cliente1.setTelefone("123456789");
        cliente1.setProfissao("Contador");
        cliente1.setEmail("punisher@mail.com");
        cliente1.setStatus(Status.ATIVO);

        Endereco enderecoC1 = new Endereco();
        enderecoC1.setRua("Avenida Paulista");
        enderecoC1.setNumero("2000");
        enderecoC1.setComplemento("Sala 8");
        enderecoC1.setBairro("Bela Vista");
        enderecoC1.setCidade("São Paulo");
        enderecoC1.setEstado("São Paulo");

        cliente1.setEndereco(enderecoC1);

        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();

        cliente2.setNome("Peter Parker");
        cliente2.setCpf("22233344455");
        cliente2.setTelefone("123456789");
        cliente2.setProfissao("Fotógrafo");
        cliente2.setEmail("spiderman@mail.com");
        cliente2.setStatus(Status.ATIVO);

        Endereco enderecoC2 = new Endereco();
        enderecoC2.setRua("Rua 1");
        enderecoC2.setNumero("2");
        enderecoC2.setComplemento("Sala 3");
        enderecoC2.setBairro("Bairro 4");
        enderecoC2.setCidade("Belo Horizonte");
        enderecoC2.setEstado("Minas Gerais");

        cliente2.setEndereco(enderecoC2);

        clienteRepository.save(cliente2);

        Cliente cliente3 = new Cliente();

        cliente3.setNome("Palmirinha");
        cliente3.setCpf("33344455566");
        cliente3.setTelefone("123456789");
        cliente3.setProfissao("Cozinheira");
        cliente3.setEmail("palmirinha@mail.com");
        cliente3.setStatus(Status.ATIVO);

        Endereco enderecoC3 = new Endereco();
        enderecoC3.setRua("Rua 10");
        enderecoC3.setNumero("0");
        enderecoC3.setComplemento("Sala 30");
        enderecoC3.setBairro("Bairro 40");
        enderecoC3.setCidade("Rio de Janeiro");
        enderecoC3.setEstado("Rio de Janeiro");

        cliente3.setEndereco(enderecoC3);

        clienteRepository.save(cliente3);
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