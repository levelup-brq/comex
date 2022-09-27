package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.Status;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.JpaUtil;

public class ProdutoDao {
	EntityManager em = JpaUtil.getEntityManager();
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public Produto buscaPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public void cadastra(Produto produto) {
		this.em.persist(produto);
	}
	
	public List<Produto> listaTodos(){
		String jpql = "SELECT c FROM Produto c";
		return this.em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> listaIndisponiveis(){
		int quantidadeEmEstoque = 0;
		String jpql = "SELECT c FROM Produto c WHERE c.quantidadeEmEstoque= :quantidadeEmEstoque";
		return this.em.createQuery(jpql, Produto.class).setParameter("quantidadeEmEstoque", quantidadeEmEstoque).getResultList();
	}
	
	
	
}
