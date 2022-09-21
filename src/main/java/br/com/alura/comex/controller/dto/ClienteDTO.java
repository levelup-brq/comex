package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCliente;

public class ClienteDTO {
	
	private String cpf;
	private String nome;
	private String tel;
	private String email;
	private StatusCliente status;
	private String local;
	
	
	
	public ClienteDTO(Cliente cliente) {
		super();
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
		this.tel = cliente.getTel();
		this.email = cliente.getEmail();
		this.status = cliente.getStatus();
		this.local = cliente.getEndereco().getCidade() + " / " + cliente.getEndereco().getEstado();
	}
	
	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public StatusCliente getStatus() {
		return status;
	}

	public String getLocal() {
		return local;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, local, nome, status, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(local, other.local) && Objects.equals(nome, other.nome) && status == other.status
				&& Objects.equals(tel, other.tel);
	}

	
	
	public static List<ClienteDTO> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
	
}
