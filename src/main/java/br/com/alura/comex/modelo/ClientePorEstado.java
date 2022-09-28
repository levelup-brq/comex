package br.com.alura.comex.modelo;


public class ClientePorEstado {

	private String estado;
	private Long qntdClientes;


	public ClientePorEstado(String estado, Long qntdClientes) {
		super();
		this.estado = estado;
		this.qntdClientes = qntdClientes;
	}

	public String getEstado() {
		return estado;
	}
	public Long getQntdClientes() {
		return qntdClientes;
	}

	@Override
	public String toString() {
		return "Quantidade de cliente por estado: "+ this.estado +": " + this.qntdClientes;
	}



}
