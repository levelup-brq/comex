package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Produto;

public class ProdutoDao {

  EntityManager entityManager;

  public ProdutoDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void cadastrar(Produto produto) {
    this.entityManager.persist(produto);
  }

  public List<Produto> buscarTodos() {
    var query = "SELECT p FROM Produto p";

    return this.entityManager
      .createQuery(query, Produto.class)
      .getResultList();
  }

  public Produto buscarPorId(Long id) {
    return this.entityManager.find(Produto.class, id);
  }

  public List<Produto> buscaProdutosIndisponiveis() {
    var query = "SELECT p FROM Produto p WHERE p.quantidadeDeEstoque = 0";

    return this.entityManager
      .createQuery(query, Produto.class)
      .getResultList();

  }

  
}
