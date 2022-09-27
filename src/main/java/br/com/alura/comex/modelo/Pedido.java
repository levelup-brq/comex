package br.com.alura.comex.modelo;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.thoughtworks.xstream.annotations.XStreamAlias;



@XStreamAlias("pedido")
public class Pedido implements Comparable<Pedido>{
	
	private String categoria;
	private String produto;
	private String cliente;

	private BigDecimal preco;
	private int quantidade;

  private LocalDate data;

  public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
    this.categoria = categoria;
    this.produto = produto;
    this.cliente = cliente;
    this.preco = preco;
    this.quantidade = quantidade;
    this.data = data;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getProduto() {
    return produto;
  }

  public String getCliente() {
    return cliente;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public LocalDate getData() {
    return data;
  }

  @Override
  public String toString() {
    return "Pedido{" +
        "categoria='" + categoria + '\'' +
        ", produto='" + produto + '\'' +
        ", cliente='" + cliente + '\'' +
        ", preco=" + preco +
        ", quantidade=" + quantidade +
        ", data=" + data +
        '}';
  	}

	@Override
	public int compareTo(Pedido pedido) {
		return this.cliente.compareTo(pedido.getCliente());
	}

}
