package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import br.com.alura.comex.modelo.Pedido;

public class ProcessadorDeXML {
	
	public static List<Pedido> processaArquivoXML(String nomeArquivo) {
        try {

        	URL recursoXML = ClassLoader.getSystemResource(nomeArquivo);
    	    Path caminhoDoArquivo = Path.of(recursoXML.toURI());
        	
    	    Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo);
    	    StringBuilder xml = new StringBuilder();
    	    
    	    while(leitorDeLinhas.hasNextLine()) {
    	    	xml.append(leitorDeLinhas.nextLine());
    	    }
    	    
    	    XStream xstream = new XStream();
    	    xstream.alias("pedidos", PedidoList.class);
    	    xstream.alias("pedido", Pedido.class);
    	    xstream.addImplicitCollection(PedidoList.class, "pedidos");
    	    xstream.addPermission(AnyTypePermission.ANY);
    	    PedidoList pedidos = (PedidoList) xstream.fromXML(xml.toString());
    	    return pedidos.getPedidos();
        }catch(URISyntaxException e) {
        	throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeArquivo));
        }catch(IOException e) {
        	throw new RuntimeException("Erro ao abrir o Scanner para processar o arquivo!");
        }       
    }
	
	

}
