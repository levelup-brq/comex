package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;

public class CategoriaForm {

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria converter () {
		return new Categoria(this.nome);
	}
}
