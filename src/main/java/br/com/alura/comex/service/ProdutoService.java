package br.com.alura.comex.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class ProdutoService {
  
  ProdutoRepository produtoRepository;
  CategoriaRepository categoriaService;

  ProdutoService(ProdutoRepository produtoRepository, 
    CategoriaRepository categoriaService) {
    this.produtoRepository = produtoRepository;
    this.categoriaService = categoriaService;
  }

  public void criar() {

    Categoria eletronicos = this.categoriaService.findById(2l).orElse(null);
    Categoria eletroeletronicos = this.categoriaService.findById(3l).orElse(null);
    Categoria livroCategoria = this.categoriaService.findById(7l).orElse(null);
    Categoria armarioCategoria = this.categoriaService.findById(5l).orElse(null);


    Produto celular = new Produto(
      "celular", 
      new BigDecimal(450), 
      2, 
      eletronicos);
    celular.setDescricao("muito bom");


    Produto livro = new Produto(
      "livro", 
      new BigDecimal(2000), 
      2, 
      livroCategoria);
      livro.setDescricao("bom");


    Produto geladeira = new Produto(
      "geladeira", 
      new BigDecimal(900), 
      0, 
      eletroeletronicos
    );
    geladeira.setDescricao("muito bom");

    Produto armario = new Produto(
      "armario", 
      new BigDecimal(750), 
      0, 
      armarioCategoria
    );
    armario.setDescricao("armario");

    this.produtoRepository.save(celular);
    this.produtoRepository.save(livro);
    this.produtoRepository.save(geladeira);
    this.produtoRepository.save(armario);
  }

  public void buscarProdutosIndisponiveis() {
    List<Produto> produtos = this.produtoRepository.buscaIndisponiveis();
    produtos.forEach(System.out::println);
  }

}
