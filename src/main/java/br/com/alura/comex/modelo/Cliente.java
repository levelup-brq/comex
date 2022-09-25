package br.com.alura.comex.modelo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(length = 11, nullable = false)
  private String cpf;

  @Column(length = 11, nullable = false)
  private String telefone;
  
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String profissao;

  @Embedded
  private Endereco endereco;

  @OneToOne
  Usuario usuario;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "status")
  private StatusDoCliente status = StatusDoCliente.ATIVO;

  public Cliente(
      String nome, 
      String cpf, 
      String telefone, 
      String email, 
      String profissao,
      Endereco endereco,
      Usuario usuario) {
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.profissao = profissao;
    this.endereco = endereco;
    this.usuario = usuario;
  }

  public Cliente() {}

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getEmail() {
    return email;
  }

  public String getProfissao() {
    return profissao;
  }

  public StatusDoCliente getStatus() {
    return status;
  }

  public void setStatus(StatusDoCliente status) {
    this.status = status;
  }
  
  public Endereco getEndereco() {
    return endereco;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Usuario getUsuario() {
    return this.usuario;
  }

  @Override
  public String toString() {
    return String.format("nome: %s, email: %s, endereco: %s", 
      this.getNome(), 
      this.getEmail(),
      this.getEndereco().getRua());
  }

}
