package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainCategoriaDao {
  public static void main(String[] args) {
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);


    Categoria eletronicos = new Categoria("eletronicos"); 
    
    Categoria games = new Categoria("games");

    Categoria informatica = new Categoria("informatica");

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronicos);
    categoriaDao.cadastrar(games);
    categoriaDao.cadastrar(informatica);

    informatica.setStatus(StatusCategoria.INATIVA);
    
    categoriaDao.buscarCategoriasAtivas().forEach(categoria -> {
      var relatorio = String
        .format("categorias ativas: %s %s", categoria.getNome(), categoria.getStatus());
      
      System.out.println(relatorio);
    });

    categoriaDao.buscarCategoriasInativas().forEach(categoria -> {
      var relatorio = String
        .format("categorias inativas: %s %s", categoria.getNome(), categoria.getStatus());
      System.out.println(relatorio);
    });

    System.out.println("categoria por Id: " + categoriaDao.buscarPorId(1l).getNome());

    categoriaDao.remover(informatica);

    categoriaDao.buscarTodos().forEach(categoria -> {
      System.out.println("categoria: " + categoria.getNome());
    });

    entityManager.getTransaction().commit();
    entityManager.close();

  }

}
