package br.com.alura.comex.repository;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    List<Categoria> findByNomeCategoria(String nome);

    @Query("SELECT c FROM Categoria c WHERE c.status = :status")
    List<Categoria> findByStatus(Status status);
}