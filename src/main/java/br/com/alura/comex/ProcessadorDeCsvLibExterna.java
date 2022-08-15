package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class ProcessadorDeCsvLibExterna {

  public static List<Pedido> processaArquivo(String nomeDoArquivo) {
    try {
      List<Pedido> pedidos = new ArrayList<>();

      URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
      Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

      CSVReader csvReader = new CSVReader(Files.newBufferedReader(caminhoDoArquivo));
      csvReader.skip(1);

      Iterator<String[]> leitorDeLinhas = csvReader.iterator();
      
      while(leitorDeLinhas.hasNext()) {
        String[] linha = leitorDeLinhas.next();

        String categoria = linha[0].toString();
        String produto = linha[1].toString();
        BigDecimal preco = new BigDecimal(linha[2].toString());
        int quantidade = Integer.parseInt(linha[3]);
        LocalDate data = LocalDate.parse(linha[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String cliente = linha[5];
        
        Pedido pedido = new Pedido(categoria, produto, cliente, preco, quantidade, data);
        pedidos.add(pedido);
      }

      return pedidos;
    } catch (URISyntaxException e) {
      throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
    } catch (IOException e) {
      throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
    }
  }

  
}
