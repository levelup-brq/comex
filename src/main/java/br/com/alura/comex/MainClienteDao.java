package br.com.alura.comex;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.util.JpaUtil;

import javax.persistence.EntityManager;


public class MainClienteDao {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteDao clienteDao = new ClienteDao(em);

        Cliente c1 = new Cliente();
        c1.setNome("Frank Castle");
        c1.setCpf("11122233344");
        c1.setTelefone("123456789");
        c1.setEmail("punisher@mail.com");

        Cliente c2 = new Cliente();
        c2.setNome("Peter Parker");
        c2.setCpf("22233344455");
        c2.setTelefone("123456789");
        c2.setEmail("spiderman@mail.com");

        Cliente c3 = new Cliente();
        c3.setNome("Palmirinha");
        c3.setCpf("33344455566");
        c3.setTelefone("123456789");
        c3.setEmail("palmirinha@mail.com");

        System.out.println("Cadastro de 3 Clientes: ");
        em.getTransaction().begin();
        clienteDao.cadastrar(c1);
        clienteDao.cadastrar(c2);
        clienteDao.cadastrar(c3);
        em.getTransaction().commit();
        clienteDao.buscarTodos().forEach(System.out::println);

        System.out.println("Alterar cliente para suspenso: ");
        em.getTransaction().begin();
        c3.setStatus(Status.SUSPENSO);
        clienteDao.atualizar(c3);
        em.getTransaction().commit();
        clienteDao.buscarTodos().forEach(System.out::println);

        System.out.println("Pesquisar cliente pelo nome: ");
        System.out.println(clienteDao.buscaClientePorNome("Peter Parker"));

        System.out.println("Pesquisar clientes ativos: ");
        clienteDao.buscaTodosPorStatus(Status.ATIVO).forEach(System.out::println);
    }
}
