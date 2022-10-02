package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoDoCliente;

public class PedidoDTO {

  private ClientePedidoDTO cliente;
  private LocalDate data;
  private BigDecimal valorTotalDoPedido;
  private BigDecimal desconto;
  private List<ListaDeItemDTO> listaDeItens;


  PedidoDTO(PedidoDoCliente pedido) {
    this.setCliente(pedido.getCliente());
    this.setListaDeItens(pedido.getItensDoPedido());
    this.data = pedido.getDate();
    this.valorTotalDoPedido = pedido.getValorTotalDoPedido();
    this.desconto = pedido.calcularTotalDeDescontosDoPedido();
  }

  public void setCliente(Cliente cliente) {
    this.cliente = ClientePedidoDTO.converter(cliente);
  }

  public ClientePedidoDTO getCliente() {
    return this.cliente;
  }

  public void setListaDeItens(List<ItemDePedido> itensDePedidos) {
    this.listaDeItens = ListaDeItemDTO.converter(itensDePedidos);
  }

  public List<ListaDeItemDTO> getListaDeItens() {
    return this.listaDeItens;
  }

  public BigDecimal getValorTotalDoPedido() {
    return valorTotalDoPedido;
  }

  public LocalDate getData() {
    return data;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public static PedidoDTO converter(PedidoDoCliente pedido) {
    return new PedidoDTO(pedido);
  }
  
}
