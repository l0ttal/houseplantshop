package swd20.Houseplantshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/css/**").permitAll()
        	.antMatchers("/img/**").permitAll()
        	.antMatchers("/", "/home", "/signup", "/saveuser").permitAll()
        	.anyRequest().authenticated()
        	.and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/home", true)
          .permitAll()
          .and()
      .logout()
          .permitAll()
          .invalidateHttpSession(true)
          .and()
      .httpBasic();
     return http.build();   
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }	
}