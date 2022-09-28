package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.StatusCliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

	public Cliente findByNome(String nome);
	
	public List<Cliente> findByStatus(StatusCliente status);
}
