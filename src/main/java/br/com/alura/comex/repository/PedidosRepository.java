package br.com.alura.comex.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.comex.modelo.PedidoDoCliente;

public interface PedidosRepository extends CrudRepository<PedidoDoCliente, Integer> {
  
}
