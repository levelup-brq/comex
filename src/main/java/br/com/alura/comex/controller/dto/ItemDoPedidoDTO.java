package br.com.alura.comex.controller.dto;

public class ItemDoPedidoDTO {
  
  private Integer quantidade;
  private Long produtoId;

  public ItemDoPedidoDTO(Integer quantidade, Long produtoId) {
    this.quantidade = quantidade;
    this.produtoId = produtoId;
  }
  
  public Integer getQuantidade() {
    return quantidade;
  }

  public Long getProdutoId() {
    return produtoId;
  }
  
  // @Override
  // public String toString() {
  //   return String.format("quantidade: %s, produtoId:%s", this.getQuantidade(), this.getProdutoId());
  // }
}
