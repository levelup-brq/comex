package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainClienteDao {
  public static void main(String[] args) {

    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    ClienteDao clienteDao = new ClienteDao(entityManager);


    var gandalf = new Cliente();
    gandalf.setNome("Gandalf");
    gandalf.setCpf("01232100055");
    gandalf.setEmail("gandalf@outlook.com");
    gandalf.setProfissao("developer");
    gandalf.setStatus(Status.ATIVO);

    var steve = new Cliente();
    steve.setNome("Steve");
    steve.setCpf("01232100055");
    steve.setEmail("steve@outlook.com");
    steve.setProfissao("Ator");
    steve.setStatus(Status.ATIVO);

    var bilbo = new Cliente();
    bilbo.setNome("Bilbo");
    bilbo.setCpf("01232100055");
    bilbo.setEmail("bilbo@outlook.com");
    bilbo.setProfissao("Explorador");
    bilbo.setStatus(Status.ATIVO);
    
    entityManager.getTransaction().begin();

    clienteDao.cadastrar(gandalf);
    clienteDao.cadastrar(bilbo);
    clienteDao.cadastrar(steve);

    entityManager.flush();


    bilbo.setStatus(Status.SUSPENSO);
    clienteDao.atualizar(bilbo);


    var nomeDoCliente = clienteDao.buscarPorNome("Bilbo").getNome();
    System.out.println(nomeDoCliente);


    clienteDao.buscarPorStatus(Status.ATIVO).forEach(cliente -> {
      System.out.print("-----------------");
      System.out.print(cliente.getNome());            
    });

    entityManager.getTransaction().commit();
    entityManager.close();
    
  }

}
