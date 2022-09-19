package br.com.alura.comex.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.modelo.Categoria;

public class ProdutoForm {

  @NotNull
  @NotEmpty
  private String nome;

  private String descricao;

  @NotNull
  @Min(value = 1)
  private BigDecimal precoUnitario;
  
  @NotNull
  @Min(value = 1)
  private Integer quantidadeEmEstoque;

  @NotNull
  private Long categoriaId;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(BigDecimal precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  public Integer getQuantidadeEmEstoque() {
    return quantidadeEmEstoque;
  }

  public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
    this.quantidadeEmEstoque = quantidadeEmEstoque;
  }

  public Long getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Long categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Produto converter(CategoriaRepository categoriaRepository) {
    Categoria categoria = categoriaRepository
      .findById(this.getCategoriaId())
      .orElse(null);

    Produto produto = new Produto(
      this.getNome(),
      this.getPrecoUnitario(),
      this.getQuantidadeEmEstoque(),
      categoria
    );
    produto.setDescricao(this.getDescricao());
    
    return produto;

  }

}
