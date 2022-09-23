package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	public List<Categoria> findByStatus(StatusCategoria status);

}
