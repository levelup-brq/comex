package br.com.alura.comex.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@Column(length=11)
	private String cpf;
	@Column(nullable=false, length=512)
	private String nome;
	@Column(nullable=false, length=14)
	private String tel;
	@Column(nullable=false, length=512)
	private String email;
	@Column(nullable=false, length=512)
	private String profissao;
	@Column(nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	@Embedded
	private Endereco endereco;
	

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

	public StatusCliente getStatus() {
		return status;
	}

	public void setStatus(StatusCliente status) {
		this.status = status;
	}
	
	public void setEndereco(String rua, String número, String complemento, String bairro, String cidade, String estado) {
		this.endereco = new Endereco(rua, número, complemento, bairro, cidade, estado);
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, nome, profissao, status, tel);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(nome, other.nome)
				&& Objects.equals(profissao, other.profissao) && Objects.equals(status, other.status)
				&& Objects.equals(tel, other.tel);
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", status=" + status + "]";
	}
	
	@PrePersist
	private void fillPersistent() {
		if (this.status == null)
			this.status = StatusCliente.ATIVO;
	}
}
