package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaDaoMain {

	public static void main(String[] args) {
		
		CategoriaDao dao = new CategoriaDao();
		
		Categoria c = new Categoria();
		c.setNome("A");
		
		dao.cadastrar(c);
		
		c = new Categoria();
		c.setNome("B");
		
		dao.cadastrar(c);
		
		c = new Categoria();
		c.setNome("C");

		
		dao.cadastrar(c);
		
		c.setStatus(StatusCategoria.INATIVA);
		dao.atualizar(c);
		
		List<Categoria> categoriasAtivas = dao.listaAtivas();
		
		System.out.println("\n*********************************************");
		System.out.println("CATEGORIAS ATIVAS");
		System.out.println("*********************************************");
		
		categoriasAtivas.stream().forEach(System.out::println);
		
		System.out.println("*********************************************\n");
	}

}
