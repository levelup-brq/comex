package br.com.alura.comex;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.util.EntityManagerFabrica;
import br.com.alura.comex.valueObjects.RelatorioDeClientesPorEstado;

public class MainClienteDao {
  public static void main(String[] args) {
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    ClienteDao clienteDao = new ClienteDao(entityManager);
    
    Endereco enderecoCliente = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco enderecoGomes = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco enderecoJonas = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Recife", 
      "Pernambuco");

    Endereco enderecoJunior = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Recife", 
      "Pernambuco");
    
    var cliente = new Cliente(
      "Gandalf", 
      "01232100055", 
      "11912344321", 
      "gandalf@outlook.com", 
      "developer", enderecoCliente);

    var clienteJonas = new Cliente(
      "Jonas", 
      "01232100055", 
      "11912344321", 
      "Jonas@outlook.com", 
      "developer",
      enderecoJonas
      );

    var clienteGomes = new Cliente(
      "Gomes", 
      "01232100055", 
      "11912344321", 
      "gomes@outlook.com", 
      "developer",
      enderecoGomes
      );

    var clienteJunior = new Cliente(
      "Gomes", 
      "01232100055", 
      "11912344321", 
      "gomes@outlook.com", 
      "developer",
      enderecoJunior
    );
    
    
    entityManager.getTransaction().begin();

    clienteDao.cadastrar(cliente);
    clienteDao.cadastrar(clienteJonas);
    clienteDao.cadastrar(clienteGomes);
    clienteDao.cadastrar(clienteJunior);

    List<RelatorioDeClientesPorEstado> totalDeClientesPorEstado = clienteDao.numeroTotalDeClientesPorEstado();
    totalDeClientesPorEstado.forEach(System.out::println);
    
  }

}
