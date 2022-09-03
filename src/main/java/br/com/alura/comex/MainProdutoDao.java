package br.com.alura.comex;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainProdutoDao {

  public static void main(String[] args) {
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);

    Categoria eletronicos = new Categoria("eletronicos");
    Categoria eletroeletronicos = new Categoria("eletroeletronicos");


    Produto celular = new Produto(
      "celular", 
      new BigDecimal(450), 
      2, 
      eletronicos);
    celular.setDescricao("muito bom");


    Produto televisao = new Produto(
      "televisao", 
      new BigDecimal(2000), 
      1, 
      eletronicos);
      televisao.setDescricao("muito bom");


    Produto geladeira = new Produto(
      "geladeira", 
      new BigDecimal(900), 
      0, 
      eletroeletronicos
    );
    geladeira.setDescricao("muito bom");

    Produto fogao = new Produto(
      "fogao", 
      new BigDecimal(750), 
      0, 
      eletroeletronicos
    );
    fogao.setDescricao("fogao");

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronicos);
    categoriaDao.cadastrar(eletroeletronicos);

    produtoDao.cadastrar(celular);
    produtoDao.cadastrar(televisao);
    produtoDao.cadastrar(geladeira);
    produtoDao.cadastrar(fogao);


    System.out.println("***Produtos indisponiveis***");
    produtoDao.buscaProdutosIndisponiveis().forEach(System.out::println);
    System.out.println("***Produtos indisponiveis***");

    Produto produto = produtoDao.buscarPorId(1l);
    System.out.println("***Produto Por Id: ***");
    System.out.println(produto);
    System.out.println("***Produto Por Id: ***");


    produtoDao.buscarTodos().forEach(System.out::println);

    entityManager.getTransaction().commit();
    entityManager.close();

  
  }

}
