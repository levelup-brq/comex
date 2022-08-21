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
    System.out.println(exception.getMessage());
    assertEquals(exception.getMessage(), "Arquivo não existe");
  }

  @Test
  public void lancar() {    
    Exception exception = assertThrows(
      NoSuchElementException.class, 
      () -> ProcessadorDeCsv.processaArquivo("pedidos.txt"));

    assertEquals(exception.getMessage(), "O arquivo não está no formado .csv");
  }


}
