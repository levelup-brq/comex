package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itensDoPedido")
public class ItemDePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 

  @Column(nullable = false)
  private BigDecimal precoUnitario;

  @Column(nullable = true)
  private Integer quantidade;

  @OneToOne
  private Produto produto;

  @OneToOne
  private PedidoDoCliente pedido;

  @Column(nullable = true)
  private BigDecimal desconto;

  @Column(nullable = false)
  private TipoDescontoItemPedido tipoDescontoItemPedido;

  public ItemDePedido(
      BigDecimal precoUnitario, 
      Integer quantidade, 
      Produto produto, 
      //PedidoDoCliente pedido,
      TipoDescontoItemPedido tipoDescontoItemPedido) {
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
    this.produto = produto;
    //this.pedido = pedido;
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

  public PedidoDoCliente getPedido() {
    return pedido;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }

  public TipoDescontoItemPedido getTipoDescontoItemPedido() {
    return tipoDescontoItemPedido;
  }





}
