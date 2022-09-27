package br.com.alura.comex.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.alura.comex.TipoDescontoPedidoEnum;

@Entity
@Table(name = "pedidos")
@XStreamAlias("pedido")
public class Pedido implements Comparable<Pedido> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;

	@ManyToOne
	private Cliente cliente;

	private BigDecimal desconto;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	private List<ItemDePedido> itens = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	TipoDescontoPedidoEnum tipoDeDesconto;
	
	private BigDecimal valorTotal = BigDecimal.ZERO;

	public Pedido(Cliente cliente, LocalDate data) {
		this.cliente = cliente;
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public List<ItemDePedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}

	public void addItemDePedido(ItemDePedido item) {
		item.setPedido(this);
		itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public TipoDescontoPedidoEnum getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(TipoDescontoPedidoEnum tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}

	@Override
	public String toString() {
		return "Pedido { Dados do cliente= " + cliente + " | " + ", | Data do pedido= " + data + " | { Itens do pedido = "+ itens + "} }";
	}

	@Override
	public int compareTo(Pedido pedido) {
		return this.cliente.getNome().compareTo(pedido.cliente.getNome());
	}

}
