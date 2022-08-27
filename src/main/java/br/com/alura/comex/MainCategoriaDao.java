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
    

    Categoria eletronicos = new Categoria(); 
    eletronicos.setNome("eletronicos");
    eletronicos.setStatus(StatusCategoria.ATIVA);
    

    Categoria games = new Categoria();
    games.setNome("games");
    games.setStatus(StatusCategoria.ATIVA);

    Categoria informatica = new Categoria();
    informatica.setNome("informatica");
    informatica.setStatus(StatusCategoria.ATIVA);

    entityManager.getTransaction().begin();

    categoriaDao.cadastrar(eletronicos);
    categoriaDao.cadastrar(games);
    categoriaDao.cadastrar(informatica);


    informatica.setStatus(StatusCategoria.INATIVA);
    
    categoriaDao.buscarCategoriasAtivas().forEach(categoria -> {
      System.out.println("--------------------");
      System.out.println(categoria.getStatus());
    });

    entityManager.getTransaction().commit();
    entityManager.close();

  }

}
