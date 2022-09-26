package br.com.alura.comex.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.PedidoDTO;
import br.com.alura.comex.controller.form.PedidoForm;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoDoClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

  @Autowired
  private PedidoDoClienteRepository pedidoRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDTO> detalhar(@PathVariable Long id) {
    PedidoDoCliente pedido = pedidoRepository.findById(id).orElse(null);
    
    if (pedido == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(PedidoDTO.converter(pedido));
  }

  @PostMapping
  public ResponseEntity<PedidoForm> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
    PedidoDoCliente pedido = form.converter(
      this.produtoRepository, 
      this.clienteRepository,
      this.pedidoRepository);
    pedidoRepository.save(pedido);
    
    URI uri = uriBuilder
      .path("/api/pedidos/{id}")
      .buildAndExpand(pedido.getId()).toUri();

    return ResponseEntity.created(uri).body(form);
  }
}
