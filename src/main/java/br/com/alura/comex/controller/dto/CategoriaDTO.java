package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaDTO {
	

		private Integer id;
		private String nome;
		private StatusCategoria status;

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

		@Override
		public int hashCode() {
			return Objects.hash(id, nome, status);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CategoriaDTO other = (CategoriaDTO) obj;
			return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && status == other.status;
		}

		@Override
		public String toString() {
			return "Categoria [id=" + id + ", nome=" + nome + ", status=" + status + "]";
		}
		
		public static List<CategoriaDTO> converter(List<Categoria> categorias) {
			return categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
		}
		
}
