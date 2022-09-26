package br.com.alura.comex.controller.dto;

import br.com.alura.comex.modelo.Cliente;

public class ClientePedidoDTO {
  
  private Long id;
  private String nome;

  public ClientePedidoDTO(Cliente cliente) {
    this.nome = cliente.getNome();
    this.id = cliente.getId();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public static ClientePedidoDTO converter(Cliente cliente) {
    return new ClientePedidoDTO(cliente);
  }
  
}
