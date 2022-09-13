package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.comex.modelo.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

  @Query("SELECT p FROM Produto p WHERE p.quantidadeDeEstoque = 0")
  List<Produto> buscaIndisponiveis();
}
