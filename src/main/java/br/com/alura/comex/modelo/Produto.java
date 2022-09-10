package br.com.alura.comex.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(length = 300, nullable = true)
  private String descricao;

  @Column(nullable = false)
  private BigDecimal precoUnitario;
  
  @Column(nullable = false)
  private Integer quantidadeDeEstoque;

  @ManyToOne
  private Categoria categoria;

  Produto() {}

  public Produto(
    String nome, 
    BigDecimal precoUnitario, 
    Integer quantidadeDeEstoque, 
    Categoria categoria) {
    this.nome = nome;
    this.precoUnitario = precoUnitario;
    this.quantidadeDeEstoque = quantidadeDeEstoque;
    this.categoria = categoria;
  }
  

  public String getNome() {
    return nome;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Integer getQuantidadeDeEstoque() {
    return quantidadeDeEstoque;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return String.format(
      "nome: %s, "
      + "precoUnitario: %s, "
      + "quantidadeDeEstoque: %s, "
      + "categoria: %s, "
      + "descricao: %s", 
      this.getNome(), 
      this.getPrecoUnitario(), 
      this.getQuantidadeDeEstoque(), 
      this.getCategoria().getNome(),
      this.getDescricao());
  }

}
