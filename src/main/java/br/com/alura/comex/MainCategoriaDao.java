package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.util.JpaUtil;

public class MainCategoriaDao {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		Categoria categoria1 = new Categoria("Informática");
		
		Categoria categoria2 = new Categoria("MÓVEIS");
		
		Categoria categoria3 = new Categoria("LIVROS");
		
		CategoriaDao dao = new CategoriaDao(em);
		
		System.out.println("cadastrar 3 categorias ativas: ");
		em.getTransaction().begin();
		dao.cadastra(categoria1);
		dao.cadastra(categoria2);
		dao.cadastra(categoria3);
		em.getTransaction().commit();
		dao.listaTodas().forEach(System.out::println);
		
		System.out.println("alterar 1 categoria para inativa: ");
		em.getTransaction().begin();
		categoria3.setStatus(StatusCategoria.INATIVA);
		dao.atualizar(categoria3);
		em.getTransaction().commit();
		dao.listaTodas().forEach(System.out::println);
		
		System.out.println("listar todas as categorias ativas: ");
		dao.listaAtivas(StatusCategoria.ATIVA).forEach(System.out::println);
		
	}
}
