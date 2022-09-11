package br.com.alura.comex.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {
  
  CategoriaRepository categoriaRepository;

  CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public void criar() {

    Arrays.asList(
      new Categoria("Informatica"),
      new Categoria("Eletronicos"),
      new Categoria("Games"),
      new Categoria("Eletrodomesticos"),
      new Categoria("Moveis"),
      new Categoria("Geek"),
      new Categoria("Livros")
    ).forEach(categoria -> this.categoriaRepository.save(categoria));

  }

  public void atualizarCategoriaParaInativa() {
    Categoria categoria = this.categoriaRepository
      .findById(1l)
      .orElse(null);

    categoria.setStatus(StatusCategoria.INATIVA);
    this.categoriaRepository.save(categoria);
  }

  public void buscarTodasCategoriasAtivas() {
    List<Categoria> buscarCategoriasAtivas = this.categoriaRepository
      .buscarAtivas(StatusCategoria.ATIVA);
  
    buscarCategoriasAtivas.forEach(System.out::println);

  }

  public void buscarTodasCategorias() {

    Pageable paginacao = PageRequest.of(1, 5, Sort.by(Sort.Direction.ASC, "nome"));
    Page<Categoria> categorias = this.categoriaRepository.findAll(paginacao);
    
    System.out.println(categorias);
		System.out.println("Pagina atual: " + categorias.getNumber());
		System.out.println("Total de categorias: " + categorias.getTotalElements());
    
    categorias.forEach(System.out::println);
    
  }

  
}
