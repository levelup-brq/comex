package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;

public class ClienteForm {
	
	@NotNull
	@NotEmpty
	private String cpf;
	@NotNull
	private String nome;
	@NotNull
	private String tel;
	@NotNull
	private String email;
	private String profissao;
	@NotNull
	private String rua;
	@NotNull
	private String numero;
	@NotNull
	private String complemento;
	@NotNull
	private String bairro;
	@NotNull
	private String cidade;
	@NotNull
	private String estado;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public void setNumero(String número) {
		this.numero = número;
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
	
	public Cliente converter () {
		Endereco endereco = new Endereco(this.rua, this.numero, this.complemento, this.bairro, this.cidade, this.estado);
		return new Cliente(this.cpf,this.nome,this.tel,this.email,this.profissao,endereco);
	}
}
