package br.com.alura.comex.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String rua;
	private String número;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	
	
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Endereco(String rua, String número, String complemento, String bairro, String cidade, String estado) {
		super();
		this.rua = rua;
		this.número = número;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNúmero() {
		return número;
	}
	public void setNúmero(String número) {
		this.número = número;
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
	
}
