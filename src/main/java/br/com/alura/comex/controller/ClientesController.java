package br.com.alura.comex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import br.com.alura.comex.controller.dto.ClientesDTO;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping
  @Cacheable(value = "listaDeClientes")
  public List<ClientesDTO> lista() {
    List<Cliente> clientes = this.clienteRepository.findAll();
    return ClientesDTO.converter(clientes);
  }

  @PostMapping
  @CacheEvict(value = "listaDeClientes", allEntries = true)
  public ResponseEntity<ClienteForm> cadastrar(@RequestBody @Valid ClienteForm clienteForm) {
    Cliente cliente = clienteForm.converter(usuarioRepository);
    this.clienteRepository.save(cliente);
    return ResponseEntity.ok(clienteForm);
  }
}
