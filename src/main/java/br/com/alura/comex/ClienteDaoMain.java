package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCliente;

public class ClienteDaoMain {

	public static void main(String[] args) {
		
		ClienteDao dao = new ClienteDao();
		
		Cliente c = new Cliente();
		c.setCpf("111");
		c.setNome("A");
		c.setTel("+5521111");
		c.setEmail("a@a.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua A", "1", null, "A", "A", "RJ");
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("222");
		c.setNome("B");
		c.setTel("+5521222");
		c.setEmail("b@b.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua B", "2", null, "B", "B", "RJ");
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("333");
		c.setNome("C");
		c.setTel("+5521333");
		c.setEmail("c@c.com");
		c.setProfissao("Analista");
		c.setEndereco("Rua C", "3", null, "C", "C", "SP");
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("444");
		c.setNome("D");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua C", "3", null, "C", "C", "SP");
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("555");
		c.setNome("E");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua E", "3", null, "E", "E", "DF");
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("666");
		c.setNome("F");
		c.setTel("+5521444");
		c.setEmail("c@c.com");
		c.setProfissao("Analista 2");
		c.setEndereco("Rua F", "3", null, "F", "F", "RJ");
		
		dao.cadastrar(c);
		
		c.setStatus(StatusCliente.SUSPENSO);
		dao.atualizar(c);
		
		Cliente c2 = new ClienteDao().buscarPorNome("C");
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCA POR NOME");
		System.out.println("*********************************************");
		
		System.out.println(c2);
		
		System.out.println("*********************************************\n");
		
		
		List<Cliente> clientesAtivos = dao.buscarPorStatus(StatusCliente.ATIVO);
		
		System.out.println("\n*********************************************");
		System.out.println("BUSCA POR STATUS");
		System.out.println("*********************************************");
		
		clientesAtivos.forEach(System.out::println);
		
		System.out.println("*********************************************\n");
		
		System.out.println("\n*********************************************");
		System.out.println("RELATORIO CLIENTES POR ESTADO");
		System.out.println("*********************************************");
		dao.relatorioClientesPorEstadoVO().forEach(System.out::println);
		
	}

}
