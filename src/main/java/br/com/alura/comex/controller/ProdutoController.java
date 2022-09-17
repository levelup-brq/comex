package br.com.alura.comex.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.ProdutoDTO;
import br.com.alura.comex.controller.form.ProdutoForm;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private CategoriaRepository categoriaRepository;
  
  @Autowired
  private ProdutoRepository produtoRepository;


  @GetMapping
  public Page<ProdutoDTO> listar(@RequestParam String page) {
    Pageable paginacao = PageRequest.of(Integer.parseInt(page), 5, Sort.by(Sort.Direction.ASC, "nome"));
    Page<Produto> produtos = this.produtoRepository.findAll(paginacao);
    return ProdutoDTO.converter(produtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProdutoDTO> detalhar(@PathVariable Long id) {
    Produto produto = this.produtoRepository.findById(id).orElse(null);
    if (produto == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(ProdutoDTO.converter(produto));
  }

  @PostMapping
  public ResponseEntity<ProdutoForm> cadastrar(@RequestBody @Valid ProdutoForm produtoForm) {
    Produto produto = produtoForm.converter(categoriaRepository);
    this.produtoRepository.save(produto);
    return ResponseEntity.status(201).body(produtoForm);
  }

}
