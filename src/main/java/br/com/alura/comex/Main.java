package br.com.alura.comex;
import java.util.List;

public class Main 
{
    public static void main( String[] args )
    {
        //List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");
        List<Pedido> pedidos = ProcessadorDeCsvLibExterna.processaArquivo("pedidos.csv");
        //List<Pedido> pedidos = ProcessadorDeXml.processaArquivo("pedidos.xml");

        for (Pedido pedido : pedidos) {
            String relatorio = String.format("%s %s %s", pedido.getCategoria(), pedido.getData(), pedido.getCliente());
            System.out.println(relatorio);
        }

    }
}
