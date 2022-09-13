package br.com.alura.comex.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.GastoTotalDePedidoPorCliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.TipoDescontoItemPedido;
import br.com.alura.comex.modelo.TipoDoDesconto;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoDoClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class PedidoService {

  PedidoDoClienteRepository pedidoDoClienteRepository;
  ClienteRepository clienteRepository;
  ProdutoRepository produtoRepository;

  PedidoService(PedidoDoClienteRepository pedidoDoClienteRepository,
    ClienteRepository clienteRepository,
    ProdutoRepository produtoRepository) {
      this.clienteRepository = clienteRepository;
      this.pedidoDoClienteRepository = pedidoDoClienteRepository;
      this.produtoRepository = produtoRepository;
  }

  public void criar() {

    Cliente clienteJonas = this.clienteRepository.buscarPorNome("Jonas");

    PedidoDoCliente pedidoDoCliente = new PedidoDoCliente(
      clienteJonas,
      TipoDoDesconto.FIDELIDADE
    );

    Produto produtoCelular = this.produtoRepository
      .findById(1l)
      .orElse(null);

    Produto produtoLivro = this.produtoRepository
      .findById(2l)
      .orElse(null);
    

    ItemDePedido primeiroItemDoPedido = new ItemDePedido(
      produtoCelular.getPrecoUnitario(),
      2,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );
    pedidoDoCliente.adicionaPedido(primeiroItemDoPedido);

    ItemDePedido segundoItemDoPedido = new ItemDePedido(
      produtoLivro.getPrecoUnitario(),
      1,
      produtoLivro,
      TipoDescontoItemPedido.NENHUM
    );
    pedidoDoCliente.adicionaPedido(segundoItemDoPedido);
    pedidoDoCliente.calculaValorTotalDoPedido();






    PedidoDoCliente segundoPedidoDoClienteJonas = new PedidoDoCliente(
      clienteJonas,
      TipoDoDesconto.FIDELIDADE
    );

    ItemDePedido itemDoSegundoPedidoDoClienteJonas = new ItemDePedido(
      produtoCelular.getPrecoUnitario(),
      1,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );
    segundoPedidoDoClienteJonas.adicionaPedido(itemDoSegundoPedidoDoClienteJonas);

    ItemDePedido segundoItemDoSegundoPedidoDoClienteJonas = new ItemDePedido(
      produtoLivro.getPrecoUnitario(),
      1,
      produtoLivro,
      TipoDescontoItemPedido.NENHUM
    );
    segundoPedidoDoClienteJonas.adicionaPedido(segundoItemDoSegundoPedidoDoClienteJonas);
    segundoPedidoDoClienteJonas.calculaValorTotalDoPedido();





    Cliente clienteGomes = this.clienteRepository.buscarPorNome("Gomes");
    PedidoDoCliente pedidoDoClienteGomes = new PedidoDoCliente(
      clienteGomes,
      TipoDoDesconto.FIDELIDADE
    );

    ItemDePedido primeiroItemPedidoGomes = new ItemDePedido(
      produtoCelular.getPrecoUnitario(),
      1,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );
    pedidoDoClienteGomes.adicionaPedido(primeiroItemPedidoGomes);
    pedidoDoClienteGomes.calculaValorTotalDoPedido();

    this.pedidoDoClienteRepository.save(pedidoDoCliente);
    this.pedidoDoClienteRepository.save(segundoPedidoDoClienteJonas);
    this.pedidoDoClienteRepository.save(pedidoDoClienteGomes);

  }

  public void buscarTodosPedidosDoCliente() {
    List<PedidoDoCliente> pedidosDoCliente = this.pedidoDoClienteRepository.buscaTodosDeUmCliente("Jonas");
    pedidosDoCliente.forEach(System.out::println);
  }

  public void buscarClientesMaisLucrativos() {
    this.pedidoDoClienteRepository
      .gastoTotalDePedidosPorCliente()
      .forEach(pedido -> {
        var relatorio = String.format("Nome: %s, total: %s", 
          pedido.getNome(), 
          pedido.getValorTotal());
          
        System.out.println(relatorio);
      });
  }
}
