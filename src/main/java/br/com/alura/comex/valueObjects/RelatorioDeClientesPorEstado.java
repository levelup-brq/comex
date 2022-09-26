package br.com.alura.comex.valueObjects;

public class RelatorioDeClientesPorEstado {
  private String estado;
  private long quantidade;

  public RelatorioDeClientesPorEstado(String estado, long quantidade) {
    this.estado = estado;
    this.quantidade = quantidade;
  }

  public String getEstado() {
    return estado;
  }

  public long getQuantidade() {
    return quantidade;
  }


  @Override
  public String toString() {
    return String.format("Estado: %s, Quantidade: %s", getEstado(), getQuantidade());
  }

}
