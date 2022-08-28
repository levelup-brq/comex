package br.com.alura.comex.dao;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.ItemDePedido;

public class ItemPedidoDao {
  EntityManager entityManager;
  
  public ItemPedidoDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void cadastrar(ItemDePedido itemPedido) {
    this.entityManager.persist(itemPedido);
  }

}
