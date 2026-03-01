package marc.espinosa.agendaspring.repositorios;

import marc.espinosa.agendaspring.entidades.Usuario;
import marc.espinosa.agendaspring.seguridad.PasswordEncryptor;
import marc.espinosa.agendaspring.seguridad.Rol;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public List<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("aitor",
                PasswordEncryptor.encrypt("1234"),
                Rol.ADMIN));
        usuarios.add(new Usuario("alicia",
                PasswordEncryptor.encrypt("1111"),
                Rol.USER));
        usuarios.add(new Usuario("pedro",
                PasswordEncryptor.encrypt("2222"),
                Rol.VIEWER));
        return usuarios;
    }
}
