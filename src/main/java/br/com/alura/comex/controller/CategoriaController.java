package br.com.alura.comex.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.CategoriaDTO;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<CategoriaDTO> lista() {
    List<Categoria> categorias = this.categoriaRepository.buscarTodas();
    return CategoriaDTO.converter(categorias);
  }

  @PostMapping
  public ResponseEntity<CategoriaForm> cadastrar(@RequestBody @Valid CategoriaForm categoriaForm) {
    Categoria categoria = categoriaForm.converter();
    this.categoriaRepository.save(categoria);
    return ResponseEntity.status(201).body(categoriaForm);
  }
  
}