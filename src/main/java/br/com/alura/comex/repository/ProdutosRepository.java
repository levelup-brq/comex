package br.com.alura.comex.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.comex.modelo.Produto;

public interface ProdutosRepository extends CrudRepository<Produto, Integer> {
  
}
