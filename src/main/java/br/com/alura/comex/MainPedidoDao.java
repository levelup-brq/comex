package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.modelo.Categoria;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.JpaUtil;

public class MainPedidoDao {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		System.out.println("testeInclusão");
		
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Renan Mattos Silva");
		cliente1.setCpf("33533533535");
		cliente1.setTelefone("12341234");
		cliente1.setEmail("rmsilva@brq.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Stafany Galvez Silva");
		cliente2.setCpf("22822822828");
		cliente2.setTelefone("78907890");
		cliente2.setEmail("ste@brq.com");
		
		Cliente cliente3 = new Cliente();
		cliente3.setNome("Lorena Galvez Silva");
		cliente3.setCpf("11411411414");
		cliente3.setTelefone("43214321");
		cliente3.setEmail("lorena@brq.com");	
		
		
		
		Categoria categoria1 = new Categoria("Informática");
		Categoria categoria2 = new Categoria("MÓVEIS");
		Categoria categoria3 = new Categoria("LIVROS");
		
		
		Produto produto1 = new Produto();
		produto1.setNome("Notebook Samsung");
		produto1.setPrecoUnitario(new BigDecimal (300.00));
		produto1.setQuantidadeEmEstoque(2);
		produto1.setCategoria(categoria1);
		
		Produto produto2 = new Produto();
		produto2.setNome("Sofá 3 lugares");
		produto2.setPrecoUnitario(new BigDecimal (300.00));
		produto2.setQuantidadeEmEstoque(5);
		produto2.setCategoria(categoria2);
		
		Produto produto3 = new Produto();
		produto3.setNome("Clean Architecture");
		produto3.setPrecoUnitario(new BigDecimal (300.00));
		produto3.setQuantidadeEmEstoque(0);
		produto3.setCategoria(categoria3);
		
		Produto produto4 = new Produto();
		produto4.setNome("Mesa de jantar 6 lugares");
		produto4.setPrecoUnitario(new BigDecimal (300.00));
		produto4.setQuantidadeEmEstoque(0);
		produto4.setCategoria(categoria2);
		
		
		
		ItemDePedido itemDePedido1 = new ItemDePedido(new BigDecimal (150.00), 2, produto1, null);
		ItemDePedido itemDePedido2 = new ItemDePedido(new BigDecimal (150.00), 2, produto2, null);
		
		
		Pedido pedido = new Pedido(cliente1, LocalDate.of(2022, 1, 23));
		pedido.setDesconto(new BigDecimal (10.00));
		pedido.setTipoDeDesconto(TipoDescontoPedidoEnum.FIDELIDADE);
		pedido.addItemDePedido(itemDePedido1);
		
		
		System.out.println("FimtesteInclusão");
		
		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin(); 
		categoriaDao.cadastra(categoria1);
		categoriaDao.cadastra(categoria2);
		categoriaDao.cadastra(categoria3);
		produtoDao.cadastra(produto1);
		produtoDao.cadastra(produto2);
		produtoDao.cadastra(produto3);
		produtoDao.cadastra(produto4);
		clienteDao.cadastrar(cliente1);
		clienteDao.cadastrar(cliente2);
		clienteDao.cadastrar(cliente3);
		pedidoDao.cadastra(pedido);
		
		em.getTransaction().commit();
		
		
		System.out.println("-------------------------------------------");
		System.out.println("cadastrar 1 pedido para um cliente ativo com 2 itens de produtos em estoque **Listar todos**");
		pedidoDao.listaTodos().forEach(System.out::println);
		System.out.println("-------------------------------------------");
		
		System.out.println("Listar todos os pedidos de um cliente cadastrado");
		pedidoDao.ListaTodosDeUmCliente(pedido.getCliente().getNome()).forEach(System.out::println);
		
		
		em.close();
		
		/*
		 * Categoria categoria2 = new Categoria("MÓVEIS");
		 * 
		 * Categoria categoria3 = new Categoria("LIVROS");
		 * 
		 * CategoriaDao dao = new CategoriaDao(em);
		 * 
		 * System.out.println("cadastrar 3 categorias ativas: ");
		 * em.getTransaction().begin(); dao.cadastra(categoria1);
		 * dao.cadastra(categoria2); dao.cadastra(categoria3);
		 * em.getTransaction().commit(); dao.listaTodas().forEach(System.out::println);
		 * 
		 * System.out.println("alterar 1 categoria para inativa: ");
		 * em.getTransaction().begin(); categoria3.setStatus(StatusCategoria.INATIVA);
		 * dao.atualizar(categoria3); em.getTransaction().commit();
		 * dao.listaTodas().forEach(System.out::println);
		 * 
		 * System.out.println("listar todas as categorias ativas: ");
		 * dao.listaAtivas(StatusCategoria.ATIVA).forEach(System.out::println);
		 */
		
	}
}
