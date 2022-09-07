package br.com.alura.comex.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.alura.comex.modelo.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
  
}
