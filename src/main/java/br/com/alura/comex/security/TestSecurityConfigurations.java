package br.com.alura.comex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Profile("test")
public class TestSecurityConfigurations {
  
  @Autowired
  private AtenticacaoService autenticacaoService;

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
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.and().csrf().disable();

    return http.build();
  }

  // Configurações de recursos estáticos(js, css, imagens, etc...)
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**");
	}
}
