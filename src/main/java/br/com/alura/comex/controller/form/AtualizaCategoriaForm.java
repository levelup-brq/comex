package br.com.alura.comex.controller.form;

import java.util.Optional;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

public class AtualizaCategoriaForm {

	private String nome;
	private StatusCategoria status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusCategoria getStatus() {
		return status;
	}

	public void setStatus(StatusCategoria status) {
		this.status = status;
	}

	public Categoria atualizar(Integer id, CategoriaRepository categoriaRepository) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		Categoria categoria = null;
		
		if (optional.isPresent()) {
			categoria = optional.get();
			categoria.setNome(nome);
			categoria.setStatus(status);
			
			categoriaRepository.save(categoria);
		}
		
		return categoria;
	}
}
