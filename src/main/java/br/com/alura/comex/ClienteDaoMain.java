package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;

public class ClienteDaoMain {

	public static void main(String[] args) {
		
		ClienteDao dao = new ClienteDao();
		
		Cliente c = new Cliente();
		c.setCpf("111");
		c.setNome("A");
		c.setStatus(true);
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("222");
		c.setNome("B");
		c.setStatus(true);
		
		dao.cadastrar(c);
		
		c = new Cliente();
		c.setCpf("333");
		c.setNome("C");
		c.setStatus(true);
		
		dao.cadastrar(c);
		
		c.setStatus(false);
		dao.atualizar(c);
		
		Cliente c2 = new ClienteDao().buscarPorNome("C");
		System.out.println(c2);
		System.out.println("**************");
		List<Cliente> clientesAtivos = dao.buscarPorStatus(true);
		clientesAtivos.stream().forEach(System.out::println);
	}

}
