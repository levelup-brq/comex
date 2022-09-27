package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.comex.modelo.Cliente;
import br.com.alura.comex.modelo.ItemDePedido;
import br.com.alura.comex.modelo.Pedido;

public class ProcessadorDeCsv {

  public static List<Pedido> processaArquivo(String nomeDoArquivo) {
    try {
      URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
      Path caminhoDoArquivo = Path.of(recursoCSV.toURI());
      
      //CSVReader csvReader = new CSVReader(Files.newBufferedReader(caminhoDoArquivo));
      //csvReader.skip(numberOfLinesToSkip: 1);

      Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

      leitorDeLinhas.nextLine();

      
      List<Pedido> pedidos = new ArrayList();
      
      
      
      
      
      while (leitorDeLinhas.hasNextLine()) {
        String linha = leitorDeLinhas.nextLine();
        String[] registro = linha.split(",");

        String categoria = registro[0];
        String produto = registro[1];
        BigDecimal preco = new BigDecimal(registro[2]);
        int quantidade = Integer.parseInt(registro[3]);
        LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Cliente cliente = new Cliente();
        cliente.setNome(registro[5]);
        
        Pedido pedido = new Pedido(cliente, data);
        
        pedidos.add(pedido);
        
      }

      return pedidos;
    } catch (URISyntaxException e) {
      throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
    } catch (IOException e) {
      throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
    }
  }
  public static Scanner processaArquivoCliente(String nomeDoArquivo) {
	  try {
	      URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
	      Path caminhoDoArquivo = Path.of(recursoCSV.toURI());
	      Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);

	      return leitorDeLinhas;

	      
	  } catch (URISyntaxException e) {
	      throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
	    } catch (IOException e) {
	      throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
	    }
  }
	    
}
