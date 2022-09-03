package br.com.alura.comex.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itens_do_pedido")
public class ItemDePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 

  @Column(nullable = false)
  private BigDecimal precoUnitario;

  @Column(nullable = false)
  private Integer quantidade;

  @ManyToOne
  private Produto produto;

  @ManyToOne
  private PedidoDoCliente pedido;

  @Column(nullable = true)
  private BigDecimal desconto;

  @Column(nullable = false)
  private TipoDescontoItemPedido tipoDescontoItemPedido;

  public ItemDePedido(
      BigDecimal precoUnitario, 
      Integer quantidade, 
      Produto produto, 
      TipoDescontoItemPedido tipoDescontoItemPedido) {
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
    this.produto = produto;
    this.tipoDescontoItemPedido = tipoDescontoItemPedido;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public TipoDescontoItemPedido getTipoDescontoItemPedido() {
    return tipoDescontoItemPedido;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }

  public void setPedido(PedidoDoCliente pedido) {
    this.pedido = pedido;
  }

  public PedidoDoCliente getPedido() {
    return pedido;
  }

}
