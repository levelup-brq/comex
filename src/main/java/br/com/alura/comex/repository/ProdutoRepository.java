package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.modelo.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

  @Query("SELECT p FROM Produto p WHERE p.quantidadeEmEstoque = 0")
  List<Produto> buscaIndisponiveis();
}
