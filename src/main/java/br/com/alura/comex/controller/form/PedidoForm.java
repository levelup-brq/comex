package br.com.alura.comex.controller.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.alura.comex.fabrica.PedidoDoClienteFabrica;
import br.com.alura.comex.controller.dto.ItemDoPedidoDTO;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoDoClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;

public class PedidoForm {
  
  @NotNull
  private Long clienteId;

  @NotNull
  private List<ItemDoPedidoDTO> itensDoPedido;

  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }

  public List<ItemDoPedidoDTO> getItensDoPedido() {
    return itensDoPedido;
  }

  public void setItensDoPedido(List<ItemDoPedidoDTO> itensDoPedido) {
    this.itensDoPedido = itensDoPedido;
  }

  public PedidoDoCliente converter(
    ProdutoRepository produtoRepository,
    ClienteRepository clienteRepository,
    PedidoDoClienteRepository pedidoDoClienteRepository) {

    PedidoDoClienteFabrica casosDeUsoPedido = new PedidoDoClienteFabrica(
      produtoRepository,
      clienteRepository,
      pedidoDoClienteRepository
    );

    return casosDeUsoPedido.criaPedidoDoCliente(this.getItensDoPedido(), getClienteId());
  }

}
