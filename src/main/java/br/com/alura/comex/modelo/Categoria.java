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

  @Column(nullable = false, unique = true, length = 250)
  private String nome;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private StatusCategoria status = StatusCategoria.ATIVA;

  public Categoria() {}

  public Long getId() {
    return id;
  }

  public Categoria(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public StatusCategoria getStatus() {
    return status;
  }

  public void setStatus(StatusCategoria status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return String.format("nome: %s, status: %s", 
      this.getNome(), 
      this.getStatus());
  }

}
