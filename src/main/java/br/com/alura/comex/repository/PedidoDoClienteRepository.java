package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.GastoTotalDePedidoPorCliente;
import br.com.alura.comex.modelo.PedidoDoCliente;

@Repository
public interface PedidoDoClienteRepository extends CrudRepository<PedidoDoCliente, Integer> {
  
  @Query("SELECT p FROM PedidoDoCliente p WHERE p.cliente.nome = :nome")
  List<PedidoDoCliente> buscaTodosDeUmCliente(String nome);

  @Query(value="SELECT pedido.id, cliente.nome, SUM(pedido.valor_total_do_pedido) AS valorTotal " 
    + "FROM pedidos AS pedido "
    + "JOIN clientes AS cliente " 
    + "WHERE cliente.id = pedido.cliente_id "
    + "GROUP BY pedido.cliente_id", nativeQuery = true)
  public List<GastoTotalDePedidoPorCliente> gastoTotalDePedidosPorCliente();

}
