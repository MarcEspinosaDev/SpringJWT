package marc.espinosa.agendaspring.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static marc.espinosa.agendaspring.seguridad.Constants.*;

@Configuration
public class JWTAuthenticationConfig {

    public String getJWTToken(String username, Rol rol){
        System.out.println(">>> Generando token para: " + username + " con rol: ROLE_" + rol.toString());
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + rol.toString());

        String token = Jwts.builder()
                .setId("agendaJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSingingKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512)
                .compact();

        return TOKEN_BEARER_PREFIX + token;
    }
}
