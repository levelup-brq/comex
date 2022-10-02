package br.com.alura.comex.modelo;

import java.math.BigDecimal;

public interface GastoTotalDePedidoPorCliente {
  Long getId();
  String getNome();
  BigDecimal getValorTotal();
}
