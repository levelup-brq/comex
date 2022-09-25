package br.com.alura.comex.controller.dto;

import java.util.List;

public class PedidoDTO {

  private Long clienteId;
  private List<ItemDoPedidoDTO> itensDoPedido;


  public Long getClienteId() {
    return clienteId;
  }
  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }
  public List<ItemDoPedidoDTO> getItensDoPedido() {
    return itensDoPedido;
  }
  public void setItensDoPedido(List<ItemDoPedidoDTO> itensDoPedido) {
    this.itensDoPedido = itensDoPedido;
  }

  
}
