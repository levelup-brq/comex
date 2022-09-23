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

import br.com.alura.comex.controller.dto.ClienteDTO;
import br.com.alura.comex.controller.form.AtualizaClienteForm;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<ClienteDTO>  cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		
		Cliente cliente = form.converter();
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getCpf()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
	}
	
	@GetMapping("/{cpf}")
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable String cpf) {
		Optional<Cliente> cliente = clienteRepository.findById(cpf);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(cliente.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{cpf}")
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<ClienteDTO>  atualizar(@PathVariable String cpf, @RequestBody @Valid AtualizaClienteForm form) {
		
		Cliente cliente = form.atualizar(cpf, clienteRepository);
		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
	
	@DeleteMapping("/{cpf}")
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@Operation(summary = "comex", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> remover(@PathVariable String cpf){
		clienteRepository.deleteById(cpf);
		return ResponseEntity.ok().build();
	}
}
