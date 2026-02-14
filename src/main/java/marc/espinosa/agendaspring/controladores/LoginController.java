package marc.espinosa.agendaspring.controladores;

import marc.espinosa.agendaspring.entidades.Usuario;
import marc.espinosa.agendaspring.repositorios.UsuarioRepository;
import marc.espinosa.agendaspring.seguridad.Constants;
import marc.espinosa.agendaspring.seguridad.JWTAuthenticationConfig;
import marc.espinosa.agendaspring.seguridad.PasswordEncryptor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    UsuarioRepository ur;

    @PostMapping("login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass)
            throws BadRequestException{
        System.out.println("controller 1");
        List<Usuario> usuarios = ur.getUsuarios();
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios){
            String decrypted = PasswordEncryptor.decrypt(usuario.getEncryptedPass());
            System.out.println(">>> decrypt resultado: " + decrypted + " | recibido: " + encryptedPass);

            if (usuario.getUsername().equals(username) &&
                    PasswordEncryptor.decrypt(usuario.getEncryptedPass())
                            .equals(encryptedPass)){
                System.out.println("controller 2");
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null){
            System.out.println("controller 3");
            throw new BadRequestException();
        }
        return jwtAuthenticationConfig.getJWTToken(
                usuarioEncontrado.getUsername(),
                usuarioEncontrado.getRol()
        );
    }
}
