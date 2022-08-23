package br.com.alura.comex.dao;

import java.util.List;

import br.com.alura.comex.model.Cliente;

public class ClienteDao extends GenericDao<Cliente, String> {

	public ClienteDao() {
		super(Cliente.class);
	}

	public List<Cliente> buscarPorStatus(Boolean status) {
		String jpql = "SELECT c FROM Cliente c WHERE c.status = ?1";
		return entityManager.createQuery(jpql, Cliente.class).setParameter(1, status).getResultList();
	}
	
	public Cliente buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome = ?1";
		return entityManager.createQuery(jpql, Cliente.class).setParameter(1, nome).getSingleResult();
	}

}
