package br.com.zupacademy.ane.mercadolivre.mercadolivre.seguranca;

import br.com.zupacademy.ane.mercadolivre.mercadolivre.autenticausuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private FiltroAutorizacaoUsuario authenticateUsuario;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/categoria").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/retorno-pagseguro/**" ).permitAll()
                .antMatchers(HttpMethod.GET, "/lista-compras").permitAll()
                .antMatchers(HttpMethod.POST,"/retorno-paypal/**" ).permitAll()
                .antMatchers(HttpMethod.POST, "/notas-fiscais/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ranking/**").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .addFilterBefore(authenticateUsuario, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**","/swagger-resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userDetails -> {
            return usuarioRepository.findByLogin(userDetails).orElseThrow(() ->
                new UsernameNotFoundException("Usuário" + userDetails + "não existe"));
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
        }
    }



