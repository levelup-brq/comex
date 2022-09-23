package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaForm {

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

	public Categoria converter () {
		return new Categoria(this.nome);
	}
}
