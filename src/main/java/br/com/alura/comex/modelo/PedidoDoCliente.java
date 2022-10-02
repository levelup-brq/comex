package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemDePedido> itensDePedido = new ArrayList<ItemDePedido>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoDoDesconto tipoDoDesconto;
  
  @Column(nullable = true)
  private BigDecimal valorTotalDoPedido = BigDecimal.ZERO;

  @Column(nullable = true)
  private BigDecimal totalDeDescontos = BigDecimal.ZERO;

  @Transient
  private BigDecimal calcularTotalDeDescontos = BigDecimal.ZERO;


  PedidoDoCliente() {}

  public PedidoDoCliente(Cliente cliente) {
    this.cliente = cliente;
    this.data = LocalDate.now();
  }

  public Long getId() {
    return id;
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

  public void setTipoDoDesconto(TipoDoDesconto tipoDoDesconto) {
    this.tipoDoDesconto = tipoDoDesconto;
  }

  public List<ItemDePedido> getItensDoPedido() {
    return this.itensDePedido;
  }

  public void setTotalDePedido(BigDecimal totalDePedido) {
    this.valorTotalDoPedido = totalDePedido;
  }

  public BigDecimal getTotalDePedido() {
    return valorTotalDoPedido;
  }

  public BigDecimal getValorTotalDoPedido() {
    return valorTotalDoPedido;
  }

  public BigDecimal getValorTotalDoDesconto() {
    return totalDeDescontos;
  }

  public void setValorTotalDoDesconto(BigDecimal totalDeDescontos) {
    this.totalDeDescontos = totalDeDescontos;
  }

  public BigDecimal getTotalDeDescontos() {
    return totalDeDescontos;
  }

  public void setTotalDeDescontos(BigDecimal totalDeDescontos) {
    this.totalDeDescontos = totalDeDescontos;
  }

  public BigDecimal getCalcularTotalDeDescontos() {
    return calcularTotalDeDescontos;
  }

  public void setCalcularTotalDeDescontos(BigDecimal calcularTotalDeDescontos) {
    this.calcularTotalDeDescontos = calcularTotalDeDescontos;
  }


  public void adicionaPedido(ItemDePedido item) {
    item.setPedido(this);
    this.itensDePedido.add(item);
  }

  public void calcularValorTotalDoPedido() {
    this.valorTotalDoPedido = this.itensDePedido
    .stream()
    .map(item -> item.getPrecoUnitario()
      .multiply(new BigDecimal(item.getQuantidade())))
    .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void aplicarDescontoPorQuantidadeDePedidos(TotalDePedidoPorCliente totalDePedidoPorCliente) {
    if (totalDePedidoPorCliente != null && totalDePedidoPorCliente.getTotalDePedido() >= 5) {

      this.setTipoDoDesconto(TipoDoDesconto.FIDELIDADE);
      this.setDesconto(new BigDecimal("0.05"));

      BigDecimal totalDoDesconto = this.getTotalDePedido().multiply(this.getDesconto());
      BigDecimal novoValorDoPedido = this.getTotalDePedido().subtract(totalDoDesconto);

      this.setTotalDePedido(novoValorDoPedido);

      BigDecimal total = this.getTotalDeDescontos().add(totalDoDesconto);
      this.setTotalDeDescontos(total);
      
    } else {
      this.setTipoDoDesconto(TipoDoDesconto.NENHUM);
    }
  }

  public BigDecimal calcularTotalDeDescontosDoPedido() {
    this.setCalcularTotalDeDescontos(this.getTotalDeDescontos());

    this.itensDePedido.forEach(item -> {
      BigDecimal total = this.getTotalDeDescontos().add(item.getTotalDeDescontos());
      this.setTotalDeDescontos(total);
    });

    return this.getTotalDeDescontos();
  }

  @Override
  public String toString() {
    return String.format("Nome: %s, Valor total: %s", 
      this.getValorTotalDoPedido(), 
      this.getCliente().getNome());
  }

}
