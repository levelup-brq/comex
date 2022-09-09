package br.com.alura.comex.repository;

import java.util.List;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaDao extends GenericDao<Categoria, Integer> {

	public CategoriaDao() {
		super(Categoria.class);
	}
	
	private List<Categoria> buscarPorStatus(StatusCategoria status) {
		String jpql = "SELECT c FROM Categoria c WHERE c.status = ?1";
		return entityManager.createQuery(jpql, Categoria.class).setParameter(1, status).getResultList();
	}
	
	
	public List<Categoria> listaAtivas (){
		return buscarPorStatus(StatusCategoria.ATIVA);
	}
	
	public List<Categoria> listaInativas (){
		return buscarPorStatus(StatusCategoria.INATIVA);
	}
}
