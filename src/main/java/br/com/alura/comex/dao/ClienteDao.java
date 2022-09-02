package br.com.alura.comex.dao;

import java.util.List;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCliente;
import br.com.alura.comex.vo.RelatorioClientesPorEstadoVO;

public class ClienteDao extends GenericDao<Cliente, String> {

	public ClienteDao() {
		super(Cliente.class);
	}

	public List<Cliente> buscarPorStatus(StatusCliente status) {
		String jpql = "SELECT c FROM Cliente c WHERE c.status = ?1";
		return entityManager.createQuery(jpql, Cliente.class).setParameter(1, status).getResultList();
	}
	
	public Cliente buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome = ?1";
		return entityManager.createQuery(jpql, Cliente.class).setParameter(1, nome).getSingleResult();
	}
	
	public List<RelatorioClientesPorEstadoVO> relatorioClientesPorEstadoVO() {
		String jpql = "SELECT new br.com.alura.comex.vo.RelatorioClientesPorEstadoVO("
				+ "cliente.endereco.estado, "
				+ "COUNT(cliente)) "
				+ "FROM Cliente cliente "
				+ "GROUP BY cliente.endereco.estado "
				+ "ORDER BY COUNT(cliente) DESC";
		return entityManager.createQuery(jpql, RelatorioClientesPorEstadoVO.class)
				.getResultList();
	}

}
