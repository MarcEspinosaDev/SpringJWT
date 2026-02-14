package marc.espinosa.agendaspring.seguridad;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static marc.espinosa.agendaspring.seguridad.Constants.*;

@Component
public class JWTAuthoritationFilter extends OncePerRequestFilter {

    private Claims setSingingKey(HttpServletRequest request){
        String jwtToken = request
                .getHeader(HEADER_AUTH_KEY)
                .replace(TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(getSingingKey(SUPER_SECRET_KEY))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private void setAuthentication(Claims claims){
        List<String> authorities = (List<String>) claims.get("authorities");
        System.out.println(">>> Authorities del token: " + authorities);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean isJWTValid(HttpServletRequest request){
        String authHeader = request.getHeader(HEADER_AUTH_KEY);
        return authHeader != null && authHeader.startsWith(TOKEN_BEARER_PREFIX);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException{
        System.out.println(">>>> Filtro JWT ejecutado, header: "
                + request.getHeader(HEADER_AUTH_KEY));
        try {
            if (isJWTValid(request)){
                Claims claims = setSingingKey(request);
                System.out.println("1");
                if (claims.get("authorities") != null) {
                    System.out.println("2");
                    setAuthentication(claims);
                } else {
                    System.out.println("3");
                    SecurityContextHolder.clearContext();
                }
            } else {
                System.out.println("4");
                SecurityContextHolder.clearContext();
            }
            System.out.println("5");
            filterChain.doFilter(request, response);
        }  catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            System.out.println("6");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}