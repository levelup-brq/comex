package br.com.alura.comex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ProcessadorCsvTest {
  
  @Test
  public void deveLancarUmaExeptionSeOArquivoCsvNaoExiste() {

    Exception exception = assertThrows(
      RuntimeException.class, 
      () -> ProcessadorDeCsv.processaArquivo("pedidoNaoExiste"));
    assertEquals(exception.getMessage(), "Arquivo não existe");
  }

  @Test
  public void DeveLancarUmaExeptionSeOArquivoNaoEstaNoFormatoDeCsv() {    
    Exception exception = assertThrows(
      NoSuchElementException.class, 
      () -> ProcessadorDeCsv.processaArquivo("pedidosVazio.csv"));

    assertEquals(exception.getMessage(), "Não foi possível processar nenhuma linha do csv");
  }


}
