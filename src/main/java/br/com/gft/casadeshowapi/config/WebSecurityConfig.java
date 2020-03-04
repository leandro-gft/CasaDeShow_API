package br.com.gft.casadeshowapi.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//    private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();	
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;	
	}
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .authenticationProvider(authProvider())
//                .jdbcAuthentication()
//                .dataSource(dataSource);
//    }
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("algaworks").password("algaworks").roles("USER"); //credenciais para acesso do spring security
//}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //tipo de proteção para evitar um tipo de ataque em específico
		
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users").permitAll()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.POST, "/casas/**", "/eventos/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT, "/casas/**", "/eventos/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/casas/**", "/users/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/casas/**", "/eventos/**").hasAuthority("ADMIN")
			.anyRequest().authenticated() //qualquer requisição precisa esta autenticada
			.and()
				.httpBasic();//métood basico de autenticação
						
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
}
