package br.com.alura.comex.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.controller.dto.TokenDTO;
import br.com.alura.comex.controller.form.LoginForm;
import br.com.alura.comex.security.TokenService;


@RestController
@RequestMapping("/api/auth")
public class AuthenticacaoController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;
  
  
  @PostMapping
  public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
    UsernamePasswordAuthenticationToken dadosLogin = form.converter();
    
    try {
      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = this.tokenService.gerarToken(authentication);
      return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
