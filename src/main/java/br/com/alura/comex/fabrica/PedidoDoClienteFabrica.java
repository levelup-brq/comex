package br.com.alura.comex.fabrica;

import java.util.List;

import br.com.alura.comex.controller.dto.ItemDoPedidoDTO;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.TotalDePedidoPorCliente;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoDoClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;

public class PedidoDoClienteFabrica {
  
  ProdutoRepository produtoRepository;
  ClienteRepository clienteRepository;
  PedidoDoClienteRepository pedidoDoClienteRepository;

  public PedidoDoClienteFabrica(
    ProdutoRepository produtoRepository,
    ClienteRepository clienteRepository,
    PedidoDoClienteRepository pedidoDoClienteRepository
  ) {
    this.produtoRepository = produtoRepository;
    this.clienteRepository = clienteRepository;
    this.pedidoDoClienteRepository = pedidoDoClienteRepository;
  }
  
  public PedidoDoCliente criaPedidoDoCliente(List<ItemDoPedidoDTO> itensDoPedido, Long clienteId) {
    Cliente cliente = clienteRepository
      .findById(clienteId)
      .orElse(null);
    
    TotalDePedidoPorCliente totalDePedidos = pedidoDoClienteRepository.totalDePedidosPorCliente(cliente.getId());

    PedidoDoCliente pedidoDoCliente = new PedidoDoCliente(cliente);
    itensDoPedido.forEach(item -> {
      Produto produto = produtoRepository.findById(item.getProdutoId()).orElse(null);
      
      if (produto.getQuantidadeEmEstoque() >= 1) {
        ItemDePedido itemDePedido = new ItemDePedido(
          produto.getPrecoUnitario(), 
          item.getQuantidade(), 
          produto);

        pedidoDoCliente.adicionaPedido(itemDePedido);
      }
      
    });

    pedidoDoCliente.calcularValorTotalDoPedido();
    pedidoDoCliente.aplicarDescontoPorQuantidadeDePedidos(totalDePedidos);
    
    return pedidoDoCliente; 
  } 
}
