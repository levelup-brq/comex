package br.com.alura.comex.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.CategoriaDTO;
import br.com.alura.comex.controller.form.AtualizaCategoriaForm;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	@Cacheable(value = "listaDeCategorias")
	public List<CategoriaDTO> lista() {
		return CategoriaDTO.converter((List<Categoria>) categoriaRepository.findAll());
	}
 
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriaDTO>  cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		
		Categoria Categoria = form.converter();
		categoriaRepository.save(Categoria);
		URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(Categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDTO(Categoria));
	}
	
	@GetMapping("/{id}")
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriaDTO> buscarCategoria(@PathVariable Integer id) {
		Optional<Categoria> Categoria = categoriaRepository.findById(id);
		
		if (Categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDTO(Categoria.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CategoriaDTO>  atualizar(@PathVariable Integer id, @RequestBody @Valid AtualizaCategoriaForm form) {
		
		Categoria Categoria = form.atualizar(id, categoriaRepository);
		return ResponseEntity.ok(new CategoriaDTO(Categoria));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> remover(@PathVariable Integer id){
		categoriaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
