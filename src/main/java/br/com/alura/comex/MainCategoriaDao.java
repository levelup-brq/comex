package br.com.alura.comex;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;

public class MainCategoriaDao {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(em);

        Categoria cat1 = new Categoria();
        cat1.setNomeCategoria("Informática");
        cat1.setStatus(Status.ATIVO);

        Categoria cat2 = new Categoria();
        cat2.setNomeCategoria("Moveis");
        cat2.setStatus(Status.ATIVO);

        Categoria cat3 = new Categoria();
        cat3.setNomeCategoria("Automotivo");
        cat3.setStatus(Status.ATIVO);

        System.out.println("\n");
        em.getTransaction().begin();
        categoriaDao.cadastrar(cat1);
        categoriaDao.cadastrar(cat2);
        categoriaDao.cadastrar(cat3);
        em.getTransaction().commit();
        categoriaDao.buscarTodos().forEach(System.out::println);
        System.out.println("Cadastro de 3 Categorias");

        System.out.println("\n");
        em.getTransaction().begin();
        cat3.setStatus(Status.SUSPENSO);
        categoriaDao.atualizar(cat3);
        em.getTransaction().commit();
        categoriaDao.buscarTodos().forEach(System.out::println);
        System.out.println("Alterar categoria para suspenso");

        System.out.println("\n");
        categoriaDao.buscaCategoriasPorStatus(Status.ATIVO).forEach(System.out::println);
        System.out.println("Pesquisar categorias ativas");
    }
}
