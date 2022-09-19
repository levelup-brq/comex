package br.com.alura.comex.repository;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    @Query("SELECT c FROM Cliente c WHERE c.status = :status")
    List<Cliente> findByStatus(Status status);

}