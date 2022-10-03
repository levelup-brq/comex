package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaDTO {
	

		private Integer id;
		private String nome;
		private StatusCategoria status;
		
		public CategoriaDTO() {}

		public CategoriaDTO(Categoria categoria) {
			this.id = categoria.getId();
			this.nome = categoria.getNome();
			this.status = categoria.getStatus();
		}

		public Integer getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public StatusCategoria getStatus() {
			return status;
		}
		
		public static List<CategoriaDTO> converter(List<Categoria> categorias) {
			return categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
		}
		
}
