package br.com.alura.comex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.comex.controller.AutenticacaoViaTokenFilter;
import br.com.alura.comex.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {
  
  @Autowired
  private AtenticacaoService autenticacaoService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  // Configurações de autenticação
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		return auth.build();
	}


  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .antMatchers(HttpMethod.GET, "/api/categorias").permitAll()
    .antMatchers(HttpMethod.GET, "/api/produtos/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
    .antMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
    .anyRequest().authenticated()
				//.and().formLogin();
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(new AutenticacaoViaTokenFilter(this.tokenService, this.usuarioRepository), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  // Configurações de recursos estáticos(js, css, imagens, etc...)
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**");
	}
}
