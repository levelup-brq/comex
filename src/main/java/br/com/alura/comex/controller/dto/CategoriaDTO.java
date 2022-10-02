package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;

public class CategoriaDTO {

  public CategoriaDTO() {}

  CategoriaDTO(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
    this.status = categoria.getStatus();
  }

  private Long id;
  private String nome;
  private StatusCategoria status = StatusCategoria.ATIVA;  

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public StatusCategoria getStatus() {
    return status;
  }

  public void setStatus(StatusCategoria status) {
    this.status = status;
  }

  public static List<CategoriaDTO> converter(List<Categoria> categorias) {
    return categorias.stream()
      .map(CategoriaDTO::new)
      .collect(Collectors.toList());
  }
}
