package br.com.alura.comex.vo;

public class RelatorioClientesPorEstadoVO {
	
	private String estado;
	private Long quantidadeClientes;
	
	
	public RelatorioClientesPorEstadoVO(String estado, Long quantidadeClientes) {
		super();
		this.estado = estado;
		this.quantidadeClientes = quantidadeClientes;
	}
	
	public String getEstado() {
		return estado;
	}
	public Long getQuantidadeClientes() {
		return quantidadeClientes;
	}

	@Override
	public String toString() {
		return "RelatorioClientesPorEstadoVO [estado=" + estado + ", quantidadeClientes=" + quantidadeClientes + "]";
	}
	
	

}
