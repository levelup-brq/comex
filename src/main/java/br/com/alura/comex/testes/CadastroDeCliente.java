package br.com.alura.comex.testes;

import javax.persistence.EntityManager;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.util.JpaUtil;

public class CadastroDeCliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNome("Renan Mattos Silva");
		cliente.setCpf("22822822828");
		cliente.setTelefone("12341234");
		cliente.setEmail("rmsilva@brq.com");
		
		
		EntityManager em = JpaUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();

	}

}
