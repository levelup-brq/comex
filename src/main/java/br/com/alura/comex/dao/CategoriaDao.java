package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Categoria;

public class CategoriaDao {

  EntityManager entityManager;
  
  public CategoriaDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void cadastrar(Categoria categoria) {
    this.entityManager.persist(categoria);
  }

  public Categoria atualizar(Categoria categoria) {
    return this.entityManager.merge(categoria);
  }

  public void remover(Categoria categoria) {
    this.entityManager.remove(categoria);
  }

  public Categoria buscarPorId(Long id) {
    return this.entityManager.find(Categoria.class, id);
  }

  public List<Categoria> buscarTodos() {
    var query = "SELECT c FROM Categoria c";

    return this.entityManager
      .createQuery(query, Categoria.class)
      .getResultList();
  }

  public List<Categoria> buscarCategoriasAtivas() {
    var query = "SELECT c FROM Categoria c WHERE c.status = 'ATIVA'";

    return this.entityManager
      .createQuery(query, Categoria.class)
      .getResultList();
  }

  public List<Categoria> buscarCategoriasInativas() {
    var query = "SELECT c FROM Categoria c WHERE c.status = 'INATIVA'";

    return this.entityManager
      .createQuery(query, Categoria.class)
      .getResultList();
  }
}