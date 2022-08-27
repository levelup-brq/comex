package br.com.alura.comex;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainProdutoDao {

  public static void main(String[] args) {
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);

    Categoria eletronicos = new Categoria("eletronicos", StatusCategoria.ATIVA);
    Categoria eletroeletronicos = new Categoria("eletroeletronicos", StatusCategoria.ATIVA);


    Produto celular = new Produto(
      "celular", 
      new BigDecimal(450), 
      2, 
      eletronicos);


    Produto televisao = new Produto(
      "televisao", 
      new BigDecimal(2000), 
      1, 
      eletronicos);


    Produto geladeira = new Produto(
      "geladeira", 
      new BigDecimal(900), 
      0, 
      eletroeletronicos
    );

    Produto fogao = new Produto(
      "fogao", 
      new BigDecimal(750), 
      0, 
      eletroeletronicos
    );

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronicos);
    categoriaDao.cadastrar(eletroeletronicos);

    produtoDao.cadastrar(celular);
    produtoDao.cadastrar(televisao);
    produtoDao.cadastrar(geladeira);
    produtoDao.cadastrar(fogao);

    produtoDao.buscaProdutosIndisponiveis().forEach(produto -> {
      var mensagemProdutoIndisponivel = String.format("Produto Indisponivel: Nome: %s | Quantidade: %s", produto.getNome(), produto.getQuantidadeDeEstoque());
      System.out.println(mensagemProdutoIndisponivel);
    });

    Produto produto = produtoDao.buscarPorId(1l);
    var mensagem = "Produto por id: Nome: %s | Preço: %s | Categoria: %s";
    var mensagemProdutoPorId = String.format(
        mensagem, 
        produto.getNome(), 
        produto.getPrecoUnitario(),
        produto.getCategoria().getNome());
    System.out.println(mensagemProdutoPorId);

    produtoDao.buscarTodos().forEach(produtoItem -> {
      System.out.println(String.format("Nome: %s", produtoItem.getNome()));
    });

    entityManager.getTransaction().commit();
    entityManager.close();

    

  }

}
