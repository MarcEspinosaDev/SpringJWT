package marc.espinosa.agendaspring.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    JWTAuthoritationFilter jwtAuthoritationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        //Login
                        .requestMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
                        // Get
                        .requestMatchers(HttpMethod.GET, "/contactos", "/contactos/**")
                        .authenticated()
                        //Post
                        .requestMatchers(HttpMethod.POST, "/contactos", "/contactos/**")
                        .hasAnyAuthority("ROLE_" + Rol.ADMIN, "ROLE_" + Rol.USER)
                        //Put
                        .requestMatchers(HttpMethod.PUT, "/contactos/**")
                        .hasAnyAuthority("ROLE_" + Rol.ADMIN, "ROLE_" + Rol.USER)
                        //Delete
                        .requestMatchers(HttpMethod.DELETE, "/contactos/**")
                        .hasAuthority("ROLE_" + Rol.ADMIN)
                        //Cualquier otra peticion autenticacion
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthoritationFilter,  UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
