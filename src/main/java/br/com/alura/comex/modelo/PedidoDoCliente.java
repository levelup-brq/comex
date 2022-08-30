package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "pedidos")
public class PedidoDoCliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 
  
  @Column(nullable = false)
  private LocalDate data;

  @ManyToOne
  private Cliente cliente;

  @Column(nullable = true)
  private BigDecimal desconto;

  @Column(nullable = false)
  @OneToMany
  private List<ItemDePedido> itemDePedido;

  @Column(nullable = false)
  private TipoDoDesconto tipoDoDesconto;

  PedidoDoCliente() {}

  public PedidoDoCliente(Cliente cliente, TipoDoDesconto tipoDoDesconto, List<ItemDePedido> itemDePedidos) {
    this.cliente = cliente;
    this.tipoDoDesconto = tipoDoDesconto;
    this.data = LocalDate.now();
    this.itemDePedido = itemDePedidos;
  }

  public LocalDate getDate() {
    return data;
  }

  public void setDate(LocalDate data) {
     this.data = data;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public BigDecimal getDesconto() {
    return this.desconto;
  }

  public void setDesconto(BigDecimal desconto) {
    this.desconto = desconto;
  }
  
  public TipoDoDesconto getTipoDoDesconto() {
    return tipoDoDesconto;
  }

  public List<ItemDePedido> getItensDoPedido() {
    return this.itemDePedido;
  }

}
