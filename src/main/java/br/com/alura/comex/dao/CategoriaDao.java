package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.StatusCategoria;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.util.JpaUtil;

public class CategoriaDao {
	EntityManager em = JpaUtil.getEntityManager();
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public Categoria buscaPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}
	
	public void cadastra(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public List<Categoria> listaTodas(){
		String jpql = "SELECT c FROM Categoria c";
		return this.em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> listaAtivas(StatusCategoria status){
		String jpql = "SELECT c FROM Categoria c WHERE c.status= :status";
		return this.em.createQuery(jpql, Categoria.class).setParameter("status", status).getResultList();
	}
	
	public List<Categoria> listaInativas(StatusCategoria status){
		String jpql = "SELECT c FROM Categoria c WHERE c.status= :status";
		return this.em.createQuery(jpql, Categoria.class).setParameter("status", status).getResultList();
	}	
}
