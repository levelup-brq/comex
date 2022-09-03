package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.PedidoDoCliente;

public class PedidoDoClienteDao {
  EntityManager entityManager;
  
  public PedidoDoClienteDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void cadastrar(PedidoDoCliente pedido) {
    this.entityManager.persist(pedido);
  }

  public PedidoDoCliente buscarPorId(Long id) {
    return this.entityManager.find(PedidoDoCliente.class, id);
  }

  public List<PedidoDoCliente> buscarTodos() {
    var query = "SELECT p FROM PedidoDoCliente p";

    return this.entityManager
      .createQuery(query, PedidoDoCliente.class)
      .getResultList();
  }

  public List<PedidoDoCliente> buscaTodosDeUmCliente(String nomeDoCliente) {
    var query = "SELECT p FROM PedidoDoCliente p WHERE p.cliente.nome = :nome";

    return this.entityManager
      .createQuery(query, PedidoDoCliente.class)
      .setParameter("nome", nomeDoCliente)
      .getResultList();
  }


  public List<PedidoDoCliente> gastoTotalDePedidosPorCliente() {
    var query = "SELECT pedido " 
    + "FROM PedidoDoCliente pedido " 
    + "ORDER BY pedido.valorTotalDoPedido DESC";
    return this.entityManager
      .createQuery(query, PedidoDoCliente.class)
      .getResultList();
  }
  
}
