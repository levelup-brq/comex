package br.com.alura.comex;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.Endereco;
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
        c1.setProfissao("Contador");
        c1.setEmail("punisher@mail.com");
        c1.setStatus(Status.ATIVO);

        Endereco enderecoC1 = new Endereco();
        enderecoC1.setRua("Avenida Paulista");
        enderecoC1.setNumero("2000");
        enderecoC1.setComplemento("Sala 8");
        enderecoC1.setBairro("Bela Vista");
        enderecoC1.setCidade("São Paulo");
        enderecoC1.setEstado("São Paulo");

        c1.setEndereco(enderecoC1);

        Cliente c2 = new Cliente();
        c2.setNome("Peter Parker");
        c2.setCpf("22233344455");
        c2.setTelefone("123456789");
        c2.setProfissao("Fotógrafo");
        c2.setEmail("spiderman@mail.com");
        c2.setStatus(Status.ATIVO);

        Endereco enderecoC2 = new Endereco();
        enderecoC2.setRua("Rua 1");
        enderecoC2.setNumero("2");
        enderecoC2.setComplemento("Sala 3");
        enderecoC2.setBairro("Bairro 4");
        enderecoC2.setCidade("Cidade 5");
        enderecoC2.setEstado("Minas Gerais");

        c2.setEndereco(enderecoC2);

        Cliente c3 = new Cliente();
        c3.setNome("Palmirinha");
        c3.setCpf("33344455566");
        c3.setTelefone("123456789");
        c3.setProfissao("Cozinheira");
        c3.setEmail("palmirinha@mail.com");
        c3.setStatus(Status.ATIVO);

        Endereco enderecoC3 = new Endereco();
        enderecoC3.setRua("Rua 10");
        enderecoC3.setNumero("0");
        enderecoC3.setComplemento("Sala 30");
        enderecoC3.setBairro("Bairro 40");
        enderecoC3.setCidade("Cidade 50");
        enderecoC3.setEstado("Rio de Janeiro");

        c3.setEndereco(enderecoC3);

        System.out.println("\n");
        em.getTransaction().begin();
        clienteDao.cadastrar(c1);
        clienteDao.cadastrar(c2);
        clienteDao.cadastrar(c3);
        em.getTransaction().commit();
        clienteDao.buscarTodos().forEach(System.out::println);
        System.out.println("Cadastro de 3 Clientes");

        System.out.println("\n");
        em.getTransaction().begin();
        c1.setStatus(Status.SUSPENSO);
        clienteDao.atualizar(c1);
        em.getTransaction().commit();
        clienteDao.buscarTodos().forEach(System.out::println);
        System.out.println("Alterar cliente para suspenso");

        System.out.println("\n");
        System.out.println(clienteDao.buscaClientePorNome("Peter Parker"));
        System.out.println("Pesquisar cliente pelo nome");

        System.out.println("\n");
        clienteDao.buscaTodosPorStatus(Status.ATIVO).forEach(System.out::println);
        System.out.println("Pesquisar clientes ativos");

        System.out.println("\n");
        clienteDao.clientePorEstado().forEach(System.out::println);
        System.out.println("Pesquisar clientes por estado");
    }
}
