package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.StatusDoCliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
  
  @Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
  Cliente buscarPorNome(String nome);
  

  @Query("SELECT c FROM Cliente c WHERE c.status = :status")
  List<Cliente> buscarPorStatus(StatusDoCliente status);
}
