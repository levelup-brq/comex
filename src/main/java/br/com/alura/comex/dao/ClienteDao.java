package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.StatusDoCliente;
import br.com.alura.comex.valueObjects.RelatorioDeClientesPorEstado;

public class ClienteDao {

  EntityManager entityManager;
  
  public ClienteDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void cadastrar(Cliente cliente) {
    this.entityManager.persist(cliente);
  }

  public Cliente atualizar(Cliente cliente) {
    return this.entityManager.merge(cliente);
  }

  public void remover(Cliente cliente) {
    this.entityManager.remove(cliente);
  }

  public Cliente buscarPorId(Long id) {
    return this.entityManager.find(Cliente.class, id);
  }

  public List<Cliente> buscarTodos() {
    var query = "SELECT c FROM Cliente c";

    return this.entityManager
      .createQuery(query, Cliente.class)
      .getResultList();
  }

  public Cliente buscarPorNome(String nome) {
    var query = "SELECT c FROM Cliente c WHERE c.nome = :nome";

    return this.entityManager
      .createQuery(query, Cliente.class)
      .setParameter("nome", nome)
      .getSingleResult();
  }

  public List<Cliente> buscarPorStatus(StatusDoCliente status) {
    var query = "SELECT c FROM Cliente c WHERE c.status = :status";

    return this.entityManager
      .createQuery(query, Cliente.class)
      .setParameter("status", status)
      .getResultList();
  }
  
  public List<RelatorioDeClientesPorEstado> numeroTotalDeClientesPorEstado() {
    var query = "SELECT new br.com.alura.comex.valueObjects.RelatorioDeClientesPorEstado(" 
      + "cliente.endereco.estado, " 
      + "COUNT(cliente.id)) " 
      + "FROM Cliente cliente "
      + "GROUP BY cliente.endereco.estado";

    return this.entityManager
      .createQuery(query, RelatorioDeClientesPorEstado.class)
      .getResultList();
  }
  
}
