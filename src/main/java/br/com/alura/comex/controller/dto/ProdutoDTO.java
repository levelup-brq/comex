package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.comex.modelo.Produto;

public class ProdutoDTO {

  private Long id;

  private String nome;

  private String descricao;

  private BigDecimal precoUnitario;
  
  private Integer quantidadeDeEstoque;

  private Long categoriaId;

  ProdutoDTO(Produto produto) {
    this.id = produto.getId();
    this.nome = produto.getNome();
    this.descricao = produto.getDescricao();
    this.precoUnitario = produto.getPrecoUnitario();
    this.quantidadeDeEstoque = produto.getQuantidadeEmEstoque();
    this.categoriaId = produto.getCategoria().getId();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Integer getQuantidadeDeEstoque() {
    return quantidadeDeEstoque;
  }

  public Long getCategoriaId() {
    return this.categoriaId;
  }

  public static Page<ProdutoDTO> converter(Page<Produto> produtos) {
    return produtos.map(ProdutoDTO::new);
  }

  public static ProdutoDTO converter(Produto produto) {
    return new ProdutoDTO(produto);
  }
  
}
