package br.com.alura.comex;

import javax.persistence.EntityManager;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
import br.com.alura.comex.util.JpaUtil;

public class MainClienteDao {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Renan Mattos Silva");
		cliente1.setCpf("33533533535");
		cliente1.setTelefone("12341234");
		cliente1.setEmail("rmsilva@brq.com");
		Endereco endereco1 = new Endereco();
		endereco1.setRua("Rua um");
		endereco1.setNumero("260");
		endereco1.setBairro("bairro um");
		endereco1.setCidade("Santo André");
		endereco1.setEstado("São Paulo");
		cliente1.setEndereco(endereco1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Stafany Galvez Silva");
		cliente2.setCpf("22822822828");
		cliente2.setTelefone("78907890");
		cliente2.setEmail("ste@brq.com");
		Endereco endereco2 = new Endereco();
		endereco2.setRua("Rua dois");
		endereco2.setNumero("510");
		endereco2.setBairro("bairro dois");
		endereco2.setCidade("cidade dois");
		endereco2.setEstado("Rio de Janeiro");
		cliente2.setEndereco(endereco2);
		
		Cliente cliente3 = new Cliente();
		cliente3.setNome("Lorena Galvez Silva");
		cliente3.setCpf("11411411414");
		cliente3.setTelefone("43214321");
		cliente3.setEmail("lorena@brq.com");
		Endereco endereco3 = new Endereco();
		endereco3.setRua("Rua três");
		endereco3.setNumero("790");
		endereco3.setBairro("bairro três");
		endereco3.setCidade("cidade três");
		endereco3.setEstado("Paraná");
		cliente3.setEndereco(endereco3);
		
		Cliente cliente4 = new Cliente();
		cliente4.setNome("Vanderlei");
		cliente4.setCpf("215151515");
		cliente4.setTelefone("54354325");
		cliente4.setEmail("vanderlei@brq.com");
		Endereco endereco4 = new Endereco();
		endereco4.setRua("Rua quatro");
		endereco4.setNumero("560");
		endereco4.setBairro("bairro quatro");
		endereco4.setCidade("cidade quatro");
		endereco4.setEstado("Paraná");
		cliente4.setEndereco(endereco4);
		
		
		
		ClienteDao dao = new ClienteDao(em);
		
		System.out.println("Cadastro de 3 Clientes ativos: ");
		em.getTransaction().begin();
		dao.cadastrar(cliente1);
		dao.cadastrar(cliente2);
		dao.cadastrar(cliente3);
		dao.cadastrar(cliente4);
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
		
		System.out.println("------------------------------------- Semana 6");
		System.out.println("Relatório de clientes por estado: ");
		dao.relatorioClientesPorEstado().forEach(System.out::println);
		
		
		
	}
}
