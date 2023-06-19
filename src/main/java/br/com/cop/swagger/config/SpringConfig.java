package br.com.cop.swagger.config;

import br.com.cop.swagger.config.authentication.AutenticacaoViaTokenFilter;
import br.com.cop.swagger.repository.UsuarioRepository;
import br.com.cop.swagger.service.TokenService;
import br.com.cop.swagger.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    private final UsuarioService usuarioService;

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SpringConfig(UsuarioService usuarioService, TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers(HttpMethod.POST,"/usuario").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET,"/usuario").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/perfil").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable().authorizeRequests()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
