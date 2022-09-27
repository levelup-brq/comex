package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.util.JpaUtil;

public class MainClienteDao {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
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
		
		ClienteDao dao = new ClienteDao(em);
		
		System.out.println("Cadastro de 3 Clientes ativos: ");
		em.getTransaction().begin();
		dao.cadastrar(cliente1);
		dao.cadastrar(cliente2);
		dao.cadastrar(cliente3);
		em.getTransaction().commit();
		dao.buscarTodos().forEach(System.out::println);
		
		System.out.println("alterar 1 dos clientes para suspenso: ");
		em.getTransaction().begin();
		cliente3.setStatus(Status.SUSPENSO);
		dao.atualizar(cliente3);
		em.getTransaction().commit();
		dao.buscarTodos().forEach(System.out::println);
		
		System.out.println("pesquisar 1 cliente pelo nome.: ");
		System.out.println(dao.buscaClientePorNome("Renan Mattos Silva"));
		
		System.out.println("pesquisar todos os clientes ativos: ");
		dao.buscaTodosPorStatus(Status.ATIVO).forEach(System.out::println);
		
	}
}
