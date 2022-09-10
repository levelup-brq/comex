package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.PedidoDoCliente;

@Repository
public interface PedidoDoClienteRepository extends CrudRepository<PedidoDoCliente, Integer> {
  
  @Query("SELECT p FROM PedidoDoCliente p WHERE p.cliente.nome = :nome")
  List<PedidoDoCliente> buscaTodosDeUmCliente(String nome);

  @Query("SELECT pedido FROM PedidoDoCliente pedido ORDER BY pedido.valorTotalDoPedido DESC")
  public List<PedidoDoCliente> gastoTotalDePedidosPorCliente();
  

}
