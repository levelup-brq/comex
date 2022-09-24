package br.com.alura.comex.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.comex.modelo.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.security.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter  {
  
  private TokenService tokenService;
  private UsuarioRepository repository;

  public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
    this.tokenService = tokenService;
    this.repository = repository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = recuperarToken(request);
    System.out.println(token);

    boolean valido = tokenService.isTokenValido(token);
    if (valido) {
      authenticarCliente(token);
    }

    filterChain.doFilter(request, response);
    
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    
    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring(7, token.length());
  }

  private void authenticarCliente(String token) {
    Long idUsuario = tokenService.getIdUsuario(token);
    Usuario usuario = this.repository.findById(idUsuario).get();

    UsernamePasswordAuthenticationToken authentication = 
      new UsernamePasswordAuthenticationToken(usuario, null, null);

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
