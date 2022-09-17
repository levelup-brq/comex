package br.com.alura.comex.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Endereco {

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false, length = 20)
  private String numero;

  @Column(nullable = true)
  private String complemento;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false, length = 100)
  private String cidade;

  @Column(nullable = false, length = 100)
  private String estado;

  public Endereco(String rua, String numero, String bairro, String cidade, String estado) {
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
  }

  public Endereco() {}

  public String getRua() {
    return rua;
  }

  public String getNumero() {
    return numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public String getEstado() {
    return estado;
  }

  public String local() {
    return String.format("%s/%s", this.getEstado(), this.getCidade());
  }
  
}
