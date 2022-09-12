package br.com.alura.comex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.StatusDoCliente;
import br.com.alura.comex.repository.ClienteRepository;

@Service
public class ClienteService {

  ClienteRepository clienteRepository;

  ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public void criar() {
    Endereco endereco = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco enderecoGomes = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco enderecoJonas = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Recife", 
      "Pernambuco");
    
    var cliente = new Cliente(
      "Gandalf", 
      "01232100055", 
      "11912344321", 
      "gandalf@outlook.com", 
      "developer", 
      endereco
    );

    var clienteGomes = new Cliente(
      "Gomes", 
      "01232100055", 
      "11912344321", 
      "gomes@outlook.com", 
      "developer",
      enderecoGomes
    );

    var clienteJonas = new Cliente(
      "Jonas", 
      "01232100055", 
      "11912344321", 
      "jonas@outlook.com", 
      "developer",
      enderecoJonas
    );

    this.clienteRepository.save(cliente);
    this.clienteRepository.save(clienteJonas);
    this.clienteRepository.save(clienteGomes);
  }

  public void buscarClientePorNome() {
    Cliente cliente = this.clienteRepository.buscarPorNome("Gandalf");
    System.out.println(cliente);
  }

  public void buscarClientePorStatusAtivo() {
    List<Cliente> clientes = this.clienteRepository.buscarPorStatus(StatusDoCliente.ATIVO);
    clientes.forEach(System.out::println);
  }

  public void atualizarClienteParaSuspenso() {
    Cliente cliente = this.clienteRepository.findById(1l).orElse(null);
    cliente.setStatus(StatusDoCliente.SUSPENSO);
    this.clienteRepository.save(cliente);
  }


}
