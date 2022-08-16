package br.com.alura.comex;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ProcessadorDeXml {
  public static List<Pedido> processaArquivo(String nomeDoArquivo) {
    try {
      URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
      
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.registerModule(new JavaTimeModule());
      
      List<Pedido> pedidos = xmlMapper
        .readValue(new File(recursoCSV.toURI()), new TypeReference<List<Pedido>>() {});
    
      return pedidos;
      
    } catch (StreamReadException e) {
      throw new RuntimeException(e.getMessage());
    } catch(URISyntaxException e) {
      throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
