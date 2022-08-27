package br.com.alura.comex.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  public Categoria() {}

  public Categoria(String nome, StatusCategoria status) {
    this.nome = nome;
    this.status = status;
  }


  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusCategoria status = StatusCategoria.ATIVA;

  public String getNome() {
    return nome;
  }


  public StatusCategoria getStatus() {
    return status;
  }

  
  public void setStatus(StatusCategoria status) {
    this.status = status;
  }

}
