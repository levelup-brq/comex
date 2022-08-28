package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.StatusDoCliente;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainClienteDao {
  public static void main(String[] args) {
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    
    ClienteDao clienteDao = new ClienteDao(entityManager);

    var gandalf = new Cliente(
      "Gandalf", 
      "01232100055", 
      "11912344321", 
      "gandalf@outlook.com", 
      "developer", 
      StatusDoCliente.ATIVO);

    var jonas = new Cliente(
      "jonas",
      "01232100055",
      "11912344321",
      "jonas@outlook.com",
      "developer",
      StatusDoCliente.ATIVO
    );

    var bilbo = new Cliente(
      "Bilbo",
      "01232100055",
      "11912344321",
      "bilbo@outlook.com",
      "Historiador",
      StatusDoCliente.ATIVO
    );
    
    entityManager.getTransaction().begin();

    clienteDao.cadastrar(gandalf);
    clienteDao.cadastrar(bilbo);
    clienteDao.cadastrar(jonas);

    entityManager.flush();


    /*Edita um cliente*/
    bilbo.setStatus(StatusDoCliente.SUSPENSO);


    /*Busca cliente por nome */
    var nomeDoCliente = clienteDao.buscarPorNome("Bilbo").getNome();
    System.out.println("cliente por nome: " + nomeDoCliente);


    /*Busca cliente por status ativo */
    clienteDao.buscarPorStatus(StatusDoCliente.ATIVO).forEach(cliente -> {
      System.out.println("cliente ativo: " + cliente.getNome());            
    });


    /*Busca cliente por Id */
    System.out.println("Cliente por id: " + clienteDao.buscarPorId(2l).getNome());


    /*Remove um cliente */
    clienteDao.remover(bilbo);


    /*Busca todos os clientes */
    clienteDao.buscarTodos().forEach(cliente -> {
      var relatorio = String.format("clientes cadastrados: %s %s ", cliente.getNome(), cliente.getEmail());
      System.out.println(relatorio);            
    });

    entityManager.getTransaction().commit();
    entityManager.close();
    
  }

}
