package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.StatusDoCliente;

public class ClientesDTO {

  private Long id;
  private String nome;
  private String cpf;
  private String email;
  private String local;
  private StatusDoCliente status; 

  public ClientesDTO(Cliente cliente) {
    this.cpf = cliente.getCpf();
    this.nome = cliente.getNome();
    this.email = cliente.getEmail();
    this.id = cliente.getId();
    this.status = cliente.getStatus();
    this.local = cliente.getEndereco().local();
  }

  public ClientesDTO() {}

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getEmail() {
    return email;
  }

  public String getLocal() {
    return local;
  }

  public StatusDoCliente getStatus() {
    return status;
  }

  public static List<ClientesDTO> converter(List<Cliente> clientes) {
    return clientes.stream()
      .map(ClientesDTO::new)
      .collect(Collectors.toList());
  }

  public static ClientesDTO converter(Cliente cliente) {
    return new ClientesDTO(cliente);
  }

}
