package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {

  @Query("SELECT categoria FROM Categoria categoria WHERE categoria.status = :status")
  List<Categoria> buscarAtivas(StatusCategoria status);

  @Query("SELECT categoria FROM Categoria categoria ORDER BY categoria.nome ASC")
  List<Categoria> buscarTodas();

}
