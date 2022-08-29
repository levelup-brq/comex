package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.ItemPedidoDao;
import br.com.alura.comex.dao.PedidoDoClienteDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.StatusDoCliente;
import br.com.alura.comex.modelo.StatusCategoria;
import br.com.alura.comex.modelo.TipoDescontoItemPedido;
import br.com.alura.comex.modelo.TipoDoDesconto;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainPedidoDao {

  public static void main(String[] args) {
    
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    PedidoDoClienteDao pedidoDao = new PedidoDoClienteDao(entityManager);
    ClienteDao clienteDao = new ClienteDao(entityManager);
    ItemPedidoDao itemPedidoDao = new ItemPedidoDao(entityManager);

    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    
    Cliente cliente = new Cliente(
      "Bilbo", 
      "12345678900", 
      "11912347834", 
      "bilbo@outlook.com",
      "developer",
      StatusDoCliente.ATIVO);
      

    Categoria eletronico = new Categoria(
      "eletronico",
      StatusCategoria.ATIVA
    );

    Categoria eletroeletronico = new Categoria(
      "eletroeletronico",
      StatusCategoria.ATIVA
    );

    Produto produtoCelular = new Produto(
      "celular", 
      new BigDecimal(800), 
      Integer.valueOf(2), 
      eletronico);

    Produto produtoGeladeira = new Produto(
      "geladeira", 
      new BigDecimal(1800), 
      Integer.valueOf(1), 
      eletroeletronico);

    Produto produtoFogao = new Produto(
      "fogao", 
      new BigDecimal(1800), 
      Integer.valueOf(1), 
      eletroeletronico);
    

    ItemDePedido primeiroItemDoPedido = new ItemDePedido(
      new BigDecimal(800),
      2,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );

    ItemDePedido segundoItemDoPedido = new ItemDePedido(
      new BigDecimal(800),
      2,
      produtoGeladeira,
      TipoDescontoItemPedido.NENHUM
    );

    ItemDePedido terceiroItemDoPedido = new ItemDePedido(
      new BigDecimal(800),
      2,
      produtoFogao,
      TipoDescontoItemPedido.NENHUM
    );

    List<ItemDePedido> itensDoPedido = Arrays.asList(primeiroItemDoPedido, segundoItemDoPedido);
    List<ItemDePedido> itensDoSegundoPedido = Arrays.asList(terceiroItemDoPedido);


    PedidoDoCliente pedido = new PedidoDoCliente(
      cliente,
      TipoDoDesconto.NENHUM,
      itensDoPedido
    );

    PedidoDoCliente segundoPedido = new PedidoDoCliente(
      cliente,
      TipoDoDesconto.NENHUM,
      itensDoSegundoPedido
    );

    entityManager.getTransaction().begin();

    clienteDao.cadastrar(cliente);

    categoriaDao.cadastrar(eletronico);
    categoriaDao.cadastrar(eletroeletronico);

    itemPedidoDao.cadastrar(primeiroItemDoPedido);
    itemPedidoDao.cadastrar(segundoItemDoPedido);
    itemPedidoDao.cadastrar(terceiroItemDoPedido);

    produtoDao.cadastrar(produtoCelular);
    produtoDao.cadastrar(produtoGeladeira);
    produtoDao.cadastrar(produtoFogao);

    pedidoDao.cadastrar(pedido);
    pedidoDao.cadastrar(segundoPedido);

    /**
     * Busca todos os pedidos de um cliente
     */
    System.out.println("---Todos os pedidos de um cliente---");
    pedidoDao
      .buscaTodosDeUmCliente("Bilbo")
      .forEach(item -> {
        var mensagem = "PEDIDO %s - %s";
        var relatorio = String
          .format(mensagem, item.getCliente().getNome(), item.getDate());
        System.out.println(relatorio);

        item.getItensDoPedido().forEach(itemDoPedido -> 
          System.out.println("Nome do produto: " + itemDoPedido.getProduto().getNome()));
      });
      System.out.println("---FIM---");
      

    /* 
     * Busca todos os pedidos
     * 
     * */
    pedidoDao
      .buscarTodos()
      .forEach(item -> {
        var mensagem = "Todos os Pedidos: %s %s";
        var relatorio = String
          .format(mensagem, item.getCliente().getNome(), item.getDate());

        System.out.println(relatorio);
      });
      
      
      

      /**
       * Busca pedido por Id
       * 
       * */
      PedidoDoCliente pedidoPorId = pedidoDao.buscarPorId(1l);
        var mensagem = "Pedido por ID: %s - %s";
        var relatorioDoPedido = String
          .format(mensagem, pedidoPorId.getCliente().getNome(), pedidoPorId.getDate());
        System.out.println(relatorioDoPedido);
        
  
    entityManager.getTransaction().commit();
    entityManager.close();
    
  }
}
