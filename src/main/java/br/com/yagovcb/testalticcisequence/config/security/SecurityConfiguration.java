package br.com.yagovcb.testalticcisequence.config.security;

import br.com.yagovcb.testalticcisequence.application.service.UserDetailService;
import br.com.yagovcb.testalticcisequence.config.security.jwt.AuthSucessHandler;
import br.com.yagovcb.testalticcisequence.config.security.jwt.JsonObjectAuthenticationFilter;
import br.com.yagovcb.testalticcisequence.config.security.jwt.JwtAuthorizationFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfiguration {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final UserDetailService usuarioDetailsService;

    private final AuthSucessHandler authSucessHandler;

    private final String secret;

    public SecurityConfiguration(AuthSucessHandler authSucessHandler,UserDetailService usuarioDetailsService, @Value("${jwt.secret}") String secret){
        this.secret = secret;
        this.usuarioDetailsService = usuarioDetailsService;
        this.authSucessHandler = authSucessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> {
                    try {
                        auth
                                .antMatchers(HttpMethod.GET, "/api/alticci/**").permitAll()
                                .antMatchers("/swagger-ui/**").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilter(authenticationFilter())
                                .addFilter(new JwtAuthorizationFilter(authenticationManager, usuarioDetailsService, secret))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSucessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/v3/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
