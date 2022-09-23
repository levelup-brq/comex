package br.com.alura.comex.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;
import br.com.alura.comex.model.StatusCliente;
import br.com.alura.comex.repository.ClienteRepository;

public class AtualizaClienteForm {
	
	

	@NotNull
	private String tel;
	@NotNull
	private String email;
	private String profissao;
	private StatusCliente status;
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
	
	public Cliente atualizar(String cpf, ClienteRepository clienteRepository) {
		Optional<Cliente> optional = clienteRepository.findById(cpf);
		Cliente cliente = null;
		
		if (optional.isPresent()) {
			cliente = optional.get();
			cliente.setEmail(email);
			cliente.setProfissao(profissao);
			cliente.setTel(tel);
			cliente.getEndereco().setBairro(bairro);
			cliente.getEndereco().setCidade(cidade);
			cliente.getEndereco().setComplemento(complemento);
			cliente.getEndereco().setEstado(estado);
			cliente.getEndereco().setNumero(numero);
			cliente.getEndereco().setRua(rua);
			
			clienteRepository.save(cliente);
		}
		
		return cliente;
	}
	
	public Cliente converter () {
		Endereco endereco = new Endereco(this.rua, this.numero, this.complemento, this.bairro, this.cidade, this.estado);
		return new Cliente(this.tel,this.email,this.profissao,endereco);
	}
}
