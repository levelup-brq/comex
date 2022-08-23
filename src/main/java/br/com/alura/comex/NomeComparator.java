package br.com.alura.comex;

import java.util.Comparator;

public class NomeComparator implements Comparator<String> {

	@Override
	public int compare(String nome1, String nome2) {
		
		return nome1.compareTo(nome2);
		
	}
	
}
