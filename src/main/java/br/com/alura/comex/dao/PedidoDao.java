package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;
import br.com.alura.comex.modelo.Produto;
import br.com.alura.comex.util.JpaUtil;

public class PedidoDao {
	EntityManager em = JpaUtil.getEntityManager();
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	
	
	public void cadastra(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public Pedido buscaPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}
	
	public List<Pedido> listaTodos(){
		String jpql = "SELECT c FROM Pedido c";
		return this.em.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public List<Pedido> ListaTodosDeUmCliente(String nome){
		String jpql = "SELECT c FROM Pedido c WHERE c.cliente.nome= :nome";
		return this.em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();
	}
	
	
	
}
