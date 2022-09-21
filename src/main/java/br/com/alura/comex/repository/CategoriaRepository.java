package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
	
	public List<Categoria> findByStatus(StatusCategoria status);

}
