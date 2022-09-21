package br.com.alura.comex.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.ClienteDTO;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	@Cacheable(value = "listaDeClientes")
	public List<ClienteDTO> lista() {
		return ClienteDTO.converter((List<Cliente>) clienteRepository.findAll());
	}
 
	@PostMapping
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteForm>  cadastrar(@RequestBody @Valid ClienteForm form) {
		clienteRepository.save(form.converter());
		return ResponseEntity.ok(form);
	}
}
