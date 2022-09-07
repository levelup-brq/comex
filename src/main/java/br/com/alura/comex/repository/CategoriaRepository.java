package br.com.alura.comex.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.comex.modelo.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
  
}
