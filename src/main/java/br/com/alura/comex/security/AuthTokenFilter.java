package br.com.alura.comex.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	

	public AuthTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean valid = tokenService.isValid(token);
		
		if(valid) {
			authClient(token);
		}
		
		filterChain.doFilter(request, response);

	}

	private void authClient(String token) {
		Long id = tokenService.getIdUsuario(token);
		Optional<Usuario> user = usuarioRepository.findById(id);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String getToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
