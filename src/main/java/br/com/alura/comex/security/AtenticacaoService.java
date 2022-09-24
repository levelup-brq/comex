package br.com.alura.comex.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.comex.modelo.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

/*
 * A classe vai consultar se o e-mail do usuario existe no banco de dados
 */
@Service
public class AtenticacaoService implements UserDetailsService  {

  @Autowired
  private UsuarioRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    Optional<Usuario> usuario = repository.findByEmail(username);
    
    if (usuario.isPresent()) {
      return usuario.get();
    }

    throw new UsernameNotFoundException("Dados invalidos");
  }

}
