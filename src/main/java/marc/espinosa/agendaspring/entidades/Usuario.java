package marc.espinosa.agendaspring.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import marc.espinosa.agendaspring.seguridad.Rol;

@Data
@AllArgsConstructor
public class Usuario {
    String username;
    String encryptedPass;
    Rol rol;
}
