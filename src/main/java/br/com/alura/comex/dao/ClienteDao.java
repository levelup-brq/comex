package br.com.alura.comex.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.comex.Status;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.RelatorioDeClientePorEstadoVO;
import br.com.alura.comex.util.JpaUtil;

public class ClienteDao {
	EntityManager em = JpaUtil.getEntityManager();
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Cliente> buscarTodos(){
		String jpql = "SELECT c FROM Cliente c";
		return this.em.createQuery(jpql, Cliente.class).getResultList();
	}
	
	public Cliente buscaPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}
	
	public void remover(Cliente cliente) {
		cliente = this.em.merge(cliente);
		this.em.remove(cliente);
	}
	
	public List<Cliente> buscaTodosPorStatus(Status status){
		String jpql = "SELECT c FROM Cliente c WHERE c.status= :status";
		return this.em.createQuery(jpql, Cliente.class).setParameter("status", status).getResultList();
	}
	
	public Cliente buscaClientePorNome(String nome){
		String jpql = "SELECT c FROM Cliente c WHERE c.nome= :nome";
		return this.em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getSingleResult();
	}

	public List<RelatorioDeClientePorEstadoVO> relatorioClientesPorEstado() {
		String jpql = "SELECT new br.com.alura.comex.modelo.RelatorioDeClientePorEstadoVO(c.endereco.estado, COUNT(c.id)) FROM Cliente c GROUP BY c.endereco.estado";
		return this.em.createQuery(jpql, RelatorioDeClientePorEstadoVO.class).getResultList();
	}
	
}
