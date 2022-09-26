package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  private BigDecimal desconto = BigDecimal.ZERO;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoDescontoItemPedido tipoDescontoItemPedido;

  @Column(nullable = true)
  private BigDecimal totalDeDescontos = BigDecimal.ZERO;

  public ItemDePedido(
      BigDecimal precoUnitario, 
      Integer quantidade, 
      Produto produto) {
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
    this.produto = produto;

    this.aplicarDescontoPorQuantidade();
  }

  public ItemDePedido() {}

  public Long getId() {
    return id;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(BigDecimal precoUnitario) {
    this.precoUnitario = precoUnitario;
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

  public void setTipoDescontoItemPedido(TipoDescontoItemPedido tipoDescontoItemPedido) {
    this.tipoDescontoItemPedido = tipoDescontoItemPedido;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }

  public PedidoDoCliente getPedido() {
    return pedido;
  }

  public void setPedido(PedidoDoCliente pedido) {
    this.pedido = pedido;
  }

  private void calcularDesconto() {
    this.setDesconto(new BigDecimal("0.10"));
    BigDecimal valorDoDesconto = this.getPrecoUnitario().multiply(this.getDesconto());
      
    BigDecimal novoPrecoDoProduto = this.getPrecoUnitario()
      .subtract(valorDoDesconto)
      .setScale(2, RoundingMode.HALF_EVEN);

    this.somarDesconto(valorDoDesconto);
    
    this.setPrecoUnitario(novoPrecoDoProduto);
  }

  private void somarDesconto(BigDecimal valorDoDesconto) {
    BigDecimal total = this.getTotalDeDescontos()
      .add(valorDoDesconto)
      .multiply(new BigDecimal(this.getQuantidade()));

    this.setTotalDeDescontos(total);
  }

  public BigDecimal getTotalDeDescontos() {
    return this.totalDeDescontos;
  }

  public void setTotalDeDescontos(BigDecimal totalDeDescontos) {
    this.totalDeDescontos = totalDeDescontos;
  }

  private void aplicarDescontoPorQuantidade() {
    if (this.getQuantidade() >= 10) {
      this.setTipoDescontoItemPedido(TipoDescontoItemPedido.QUANTIDADE);
      this.calcularDesconto();
    } else {
      this.setTipoDescontoItemPedido(TipoDescontoItemPedido.NENHUM);
    }
  }
}
