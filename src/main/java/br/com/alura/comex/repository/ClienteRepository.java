package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String>{

	public Cliente findByNome(String nome);
	
	public List<Cliente> findByStatus(StatusCliente status);
}
