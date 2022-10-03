package br.com.alura.comex.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${comex.jwt.expiration}")
	private String expiration;
	
	@Value("${comex.jwt.secret}")
	private String secret;

	public String generateToken(Authentication auth) {
		
		Usuario logado = (Usuario) auth.getPrincipal();
		Date hoje = new Date();
		Date exp = new Date(hoje.getTime() + Long.parseLong(this.expiration));
		
		return Jwts.builder()
					.setIssuer("API COMEX")
					.setSubject(logado.getId().toString())
					.setIssuedAt(hoje)
					.setExpiration(exp)
					.signWith(SignatureAlgorithm.HS256, this.secret)
					.compact();
	}

	public boolean isValid(String token) {
		
		try {
			
			Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token);
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	public Long getIdUsuario(String token) {
		
		Claims body = Jwts.parser()
		.setSigningKey(this.secret)
		.parseClaimsJws(token).getBody();
		
		return Long.parseLong(body.getSubject());
	}

}
