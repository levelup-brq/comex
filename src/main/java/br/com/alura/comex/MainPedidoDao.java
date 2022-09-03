package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDoClienteDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.PedidoDoCliente;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.modelo.TipoDescontoItemPedido;
import br.com.alura.comex.modelo.TipoDoDesconto;
import br.com.alura.comex.util.EntityManagerFabrica;

public class MainPedidoDao {

  public static void main(String[] args) {
    
    EntityManager entityManager = EntityManagerFabrica.getEntityManager();
    PedidoDoClienteDao pedidoDao = new PedidoDoClienteDao(entityManager);
    ClienteDao clienteDao = new ClienteDao(entityManager);

    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    
    Endereco primeiroEndereco = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco segundoEndereco = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Sao Paulo", 
      "Sao Paulo");

    Endereco terceiroEndereco = new Endereco(
      "rua 6", 
      "456", 
      "central", 
      "Recife", 
      "Pernambuco");
      
    Cliente primeiroCliente = new Cliente(
      "Bilbo", 
      "12345678900", 
      "11912347834", 
      "bilbo@outlook.com",
      "developer",
      primeiroEndereco
    );
    
    Cliente segundoCliente = new Cliente(
      "Gandaf", 
      "12345678900", 
      "11912347834", 
      "gandalf@outlook.com",
      "developer",
      segundoEndereco
    );

    Cliente terceiroCliente = new Cliente(
      "Frodo", 
      "12345678900", 
      "11912347834", 
      "frodo@outlook.com",
      "developer",
      terceiroEndereco
    );
      


    Categoria eletronico = new Categoria(
      "eletronico"
    );

    Categoria eletroeletronico = new Categoria(
      "eletroeletronico"
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



    PedidoDoCliente primeiroPedido = new PedidoDoCliente(
      primeiroCliente,
      TipoDoDesconto.FIDELIDADE
    );
  
    PedidoDoCliente segundoPedido = new PedidoDoCliente(
      segundoCliente,
      TipoDoDesconto.FIDELIDADE
    );
  
    PedidoDoCliente terceiroPedido = new PedidoDoCliente(
      terceiroCliente,
      TipoDoDesconto.NENHUM
    );
    


    ItemDePedido primeiroItemDoPedido = new ItemDePedido(
      new BigDecimal(800),
      2,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );
    primeiroPedido.adicionaPedido(primeiroItemDoPedido);
    primeiroPedido.calculaValorTotalDoPedido();

    ItemDePedido segundoItemDoPedido = new ItemDePedido(
      new BigDecimal(1800),
      4,
      produtoGeladeira,
      TipoDescontoItemPedido.NENHUM
    );
    segundoPedido.adicionaPedido(segundoItemDoPedido);
    segundoPedido.calculaValorTotalDoPedido();

    ItemDePedido terceiroItemDoPedido = new ItemDePedido(
      new BigDecimal(1800),
      1,
      produtoFogao,
      TipoDescontoItemPedido.NENHUM
    );
    ItemDePedido quartoItemDoPedido = new ItemDePedido(
      new BigDecimal(800),
      2,
      produtoCelular,
      TipoDescontoItemPedido.NENHUM
    );
    terceiroPedido.adicionaPedido(terceiroItemDoPedido);
    terceiroPedido.adicionaPedido(quartoItemDoPedido);
    terceiroPedido.calculaValorTotalDoPedido();


    entityManager.getTransaction().begin();

    clienteDao.cadastrar(primeiroCliente);
    clienteDao.cadastrar(segundoCliente);
    clienteDao.cadastrar(terceiroCliente);
    
    categoriaDao.cadastrar(eletronico);
    categoriaDao.cadastrar(eletroeletronico);

    produtoDao.cadastrar(produtoCelular);
    produtoDao.cadastrar(produtoGeladeira);
    produtoDao.cadastrar(produtoFogao);

    pedidoDao.cadastrar(primeiroPedido);
    pedidoDao.cadastrar(segundoPedido);
    pedidoDao.cadastrar(terceiroPedido);

    pedidoDao
      .gastoTotalDePedidosPorCliente()
      .forEach(System.out::println);
  

    entityManager.getTransaction().commit();
    entityManager.close();
    
  }
}
