package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.ItemDePedido;

public class ListaDeItemDTO {


  private Long id;
  private Long produtoId;
  private String nomeDoProduto;
  private String nomeCategoria;
  private Integer quantidade;
  private BigDecimal precoUnitario;
  private BigDecimal valorPagoDoPedido;
  private BigDecimal desconto;

  public ListaDeItemDTO(Long id, Long produtoId, String nomeDoProduto, String nomeCategoria, Integer quantidade,
      BigDecimal precoUnitario, BigDecimal valorPagoDoPedido, BigDecimal desconto) {
    this.id = id;
    this.produtoId = produtoId;
    this.nomeDoProduto = nomeDoProduto;
    this.nomeCategoria = nomeCategoria;
    this.quantidade = quantidade;
    this.precoUnitario = precoUnitario;
    this.valorPagoDoPedido = valorPagoDoPedido;
    this.desconto = desconto;
  }

  public Long getId() {
    return id;
  }

  public Long getProdutoId() {
    return produtoId;
  }

  public String getNomeDoProduto() {
    return nomeDoProduto;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }


  public Integer getQuantidade() {
    return quantidade;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }


  public BigDecimal getValorPagoDoPedido() {
    return valorPagoDoPedido;
  }


  public void setValorPagoDoPedido(BigDecimal valorPagoDoPedido) {
    this.valorPagoDoPedido = valorPagoDoPedido;
  }


  public BigDecimal getDesconto() {
    return desconto;
  }


  public static List<ListaDeItemDTO> converter(List<ItemDePedido> itensDePedidos) {
    return itensDePedidos.stream().map(item -> {
      return new ListaDeItemDTO(
      item.getId(), 
      item.getProduto().getId(),
      item.getProduto().getNome(),
      item.getProduto().getCategoria().getNome(),
      item.getQuantidade(),
      item.getProduto().getPrecoUnitario(),
      item.calcularValorPagoNoAtoDaCompra(),
      item.getDesconto());
      
    }).collect(Collectors.toList());
  }

}
