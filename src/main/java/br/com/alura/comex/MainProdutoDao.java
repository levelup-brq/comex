package br.com.alura.comex;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.JpaUtil;

public class MainProdutoDao {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		Categoria categoria1 = new Categoria("Informática");
		Categoria categoria2 = new Categoria("MÓVEIS");
		Categoria categoria3 = new Categoria("LIVROS");
				
		Produto produto1 = new Produto();
		produto1.setNome("Notebook Samsung");
		produto1.setPrecoUnitario(new BigDecimal (300.00));
		produto1.setQuantidadeEmEstoque(2);
		produto1.setCategoria(categoria1);
		
		Produto produto2 = new Produto();
		produto2.setNome("Sofá 3 lugares");
		produto2.setPrecoUnitario(new BigDecimal (300.00));
		produto2.setQuantidadeEmEstoque(5);
		produto2.setCategoria(categoria2);
		
		Produto produto3 = new Produto();
		produto3.setNome("Clean Architecture");
		produto3.setPrecoUnitario(new BigDecimal (300.00));
		produto3.setQuantidadeEmEstoque(0);
		produto3.setCategoria(categoria3);
		
		Produto produto4 = new Produto();
		produto4.setNome("Mesa de jantar 6 lugares");
		produto4.setPrecoUnitario(new BigDecimal (300.00));
		produto4.setQuantidadeEmEstoque(0);
		produto4.setCategoria(categoria2);
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin(); 
		categoriaDao.cadastra(categoria1);
		categoriaDao.cadastra(categoria2);
		categoriaDao.cadastra(categoria3);
		
		produtoDao.cadastra(produto1);
		produtoDao.cadastra(produto2);
		produtoDao.cadastra(produto3);
		produtoDao.cadastra(produto4);
		
		em.getTransaction().commit();
		
		
		
		System.out.println("4 PRODUTOS CADASTRADOS - 2 PRODUTOS COM ESTOQUE E 2 PRODUTOS SEM ESTOQUE  : ");
		produtoDao.listaTodos().forEach(System.out::println);
		
		System.out.println("LISTAR OS PRODUTOS INDISPONÍVEIS");
		produtoDao.listaIndisponiveis().forEach(System.out::println);
		
		
		em.close();
		
		/*
		 * Categoria categoria2 = new Categoria("MÓVEIS");
		 * 
		 * Categoria categoria3 = new Categoria("LIVROS");
		 * 
		 * CategoriaDao dao = new CategoriaDao(em);
		 * 
		 * System.out.println("cadastrar 3 categorias ativas: ");
		 * em.getTransaction().begin(); dao.cadastra(categoria1);
		 * dao.cadastra(categoria2); dao.cadastra(categoria3);
		 * em.getTransaction().commit(); dao.listaTodas().forEach(System.out::println);
		 * 
		 * System.out.println("alterar 1 categoria para inativa: ");
		 * em.getTransaction().begin(); categoria3.setStatus(StatusCategoria.INATIVA);
		 * dao.atualizar(categoria3); em.getTransaction().commit();
		 * dao.listaTodas().forEach(System.out::println);
		 * 
		 * System.out.println("listar todas as categorias ativas: ");
		 * dao.listaAtivas(StatusCategoria.ATIVA).forEach(System.out::println);
		 */
		
	}
}
