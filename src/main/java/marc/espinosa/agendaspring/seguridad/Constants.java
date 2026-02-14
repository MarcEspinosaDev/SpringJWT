package marc.espinosa.agendaspring.seguridad;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static final String LOGIN_URL = "/login";
    public static final String HEADER_AUTH_KEY = "token";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    public static final String USER = "aitor";
    public static final String PASS = "1234";

    public static final String SECRET_KEY = "1234567890123456";
    public static final String INIT_VECTOR = "1234567890123456";

    //JWT
    public static final String SUPER_SECRET_KEY = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVl" +
            "bnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3" +
            "BhcmFiYXNlNjQ=";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;

    public static Key getSingingKey(String secret){
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
