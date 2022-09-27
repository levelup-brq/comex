package br.com.alura.comex.modelo;

public class RelatorioDeClientePorEstadoVO {
		
	private long quantidadeDeClientePorEstado;
	private String estado;
	
	public RelatorioDeClientePorEstadoVO() {}
	
	public RelatorioDeClientePorEstadoVO(String estado, long quantidadePorEstado) {
		this.estado = estado;
		this.quantidadeDeClientePorEstado = quantidadePorEstado;
	}
	
	
	public long getQuantidadeDeClientePorEstado() {
		return quantidadeDeClientePorEstado;
	}


	public void setQuantidadeDeClientePorEstado(long quantidadeDeClientePorEstado) {
		this.quantidadeDeClientePorEstado = quantidadeDeClientePorEstado;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Relatório de cliente por estado {  Quantidade de cliente do estado "+ this.estado +": " + this.quantidadeDeClientePorEstado;
	}	
	
}
