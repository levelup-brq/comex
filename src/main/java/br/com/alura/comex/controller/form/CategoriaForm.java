package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.comex.modelo.Categoria;

public class CategoriaForm {

  @NotNull
  @NotEmpty
  @Size(min = 5, max = 250)
  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria converter() {
    return new Categoria(this.getNome());
  }

}
