package br.com.alura.comex.config;

public class ErroDeFormulario {
  private String campo;
  private String erro;

  public ErroDeFormulario(String campo, String erro) {
    this.campo = campo;
    this.erro = erro;
  }

  public String getErro() {
    return erro;
  }

  public String getCampo() {
    return campo;
  }
}
