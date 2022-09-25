package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

public class ClienteForm {    

  //ATRIBUTOS PARA CLIENTE
  @NotNull
  @NotEmpty
  private String nome;

  @NotNull
  @NotEmpty
  private String cpf;

  @NotNull
  @NotEmpty
  private String telefone;
  
  @NotNull
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  private String profissao;

  @NotNull
  private Long usuarioId;

  // Atributos para ENDERECO
  @NotNull
  @NotEmpty
  private String rua;

  @NotNull
  @NotEmpty
  private String numero;

  private String complemento;

  @NotNull
  @NotEmpty
  private String bairro;

  @NotNull
  @NotEmpty
  private String cidade;

  @NotNull
  @NotEmpty
  private String estado;


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProfissao() {
    return profissao;
  }

  public void setProfissao(String profissao) {
    this.profissao = profissao;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
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

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Cliente converter(UsuarioRepository usuarioRepository) {
    Usuario usuario = usuarioRepository
      .findById(this.getUsuarioId())
      .orElse(null);

    Endereco endereco = new Endereco(
      this.getRua(),
      this.getNumero(),
      this.getBairro(),
      this.getCidade(),
      this.getEstado()
    );

    return new Cliente(
      this.getNome(),
      this.getCpf(),
      this.getTelefone(),
      this.getEmail(),
      this.getProfissao(),
      endereco,
      usuario
    );

  }

}
