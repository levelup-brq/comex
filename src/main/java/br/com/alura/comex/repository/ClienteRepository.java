package br.com.alura.comex.repository;

import br.com.alura.comex.Status;
import br.com.alura.comex.modelo.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

        List<Cliente> findByNome(String nome);

        @Query("SELECT c FROM Cliente c WHERE c.status = :status")
        List<Cliente> findByStatus(Status status);

}