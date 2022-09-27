package br.com.alura.comex.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.alura.comex.TipoDescontoItemEnum;

@Entity
@Table(name = "itensDePedidos")
@XStreamAlias("itenDePedido")
public class ItemDePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal precoUnitario;
	private Integer quantidade;
	private BigDecimal valor;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Pedido pedido;

	private BigDecimal desconto;

	@Enumerated(EnumType.STRING)
	TipoDescontoItemEnum tipoDeDesconto = TipoDescontoItemEnum.NENHUM;

	public ItemDePedido(BigDecimal precoUnitario, int quantidade, Produto produto, Pedido pedido) {

		this.precoUnitario = produto.getPrecoUnitario();
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.valor = precoUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDescontoItemEnum getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(TipoDescontoItemEnum tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Item do pedido { Nome do produto= " + produto + '\'' + " | Quantidade em estoque= " + quantidade + '}';
	}

}
