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
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

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
        String cliente = registro[5];

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
