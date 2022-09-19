//package br.com.alura.comex.dao;
//
//import br.com.alura.comex.modelo.Status;
//import br.com.alura.comex.modelo.Categoria;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//public class CategoriaDao {
//
//    private EntityManager em;
//
//    public CategoriaDao(EntityManager entityManager) {
//        this.em = entityManager;
//    }
//
//    public List<Categoria> buscarTodos() {
//        String jpql = "SELECT cat FROM Categoria cat";
//        return this.em.createQuery(jpql, Categoria.class).getResultList();
//    }
//
//    public Categoria buscaPorId(Long id) {
//        return this.em.find(Categoria.class, id);
//    }
//
//    public void cadastrar(Categoria categoria) {
//        this.em.persist(categoria);
//    }
//
//    public void atualizar(Categoria categoria) {
//        this.em.merge(categoria);
//    }
//
//    public void remover(Categoria categoria) {
//        categoria = this.em.merge(categoria);
//        this.em.remove(categoria);
//    }
//
//    public List<Categoria> buscaCategoriasPorStatus(Status status) {
//        String jpql = "SELECT cat FROM Categoria cat WHERE cat.status= :status";
//        return this.em.createQuery(jpql, Categoria.class).setParameter("status", status).getResultList();
//    }
//
//    public Categoria buscaTodasCategorias(String nome) {
//        String jpql = "SELECT cat FROM Categoria c WHERE c.nome= :nome";
//        return this.em.createQuery(jpql, Categoria.class).setParameter("nome", nome).getSingleResult();
//    }
//
//    public Categoria buscaCategoriasAtivas(Status status) {
//        String jpql = "SELECT cat FROM Categoria cat WHERE cat.status= :status";
//        return this.em.createQuery(jpql, Categoria.class).setParameter("status", status).getSingleResult();
//    }
//
//    public Categoria buscaCategoriasInativas(Status status) {
//        String jpql = "SELECT cat FROM Categoria cat WHERE cat.status= :status";
//        return this.em.createQuery(jpql, Categoria.class).setParameter("status", status).getSingleResult();
//    }
//
//}