package br.com.alura.comex;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ComexApplication implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public ComexApplication(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ComexApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        inicializadorCliente();
    }
    private void inicializadorCliente() {
        buscarTodos();
        buscaClientePorNome();
        buscaTodosPorStatus();
        salvarClientes();
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

    public void salvarClientes() {
        System.out.println("Salvar clientes ");

        Cliente cliente = new Cliente();

        cliente.setNome("Frank Castle");
        cliente.setCpf("11122233344");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Contador");
        cliente.setEmail("punisher@mail.com");
        cliente.setStatus(Status.ATIVO);

        cliente = new Cliente();

        cliente.setNome("Peter Parker");
        cliente.setCpf("22233344455");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Fotógrafo");
        cliente.setEmail("spiderman@mail.com");
        cliente.setStatus(Status.ATIVO);

        cliente = new Cliente();
        cliente.setNome("Palmirinha");
        cliente.setCpf("33344455566");
        cliente.setTelefone("123456789");
        cliente.setProfissao("Cozinheira");
        cliente.setEmail("palmirinha@mail.com");
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);
    }
}