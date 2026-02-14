package marc.espinosa.agendaspring.repositorios;

import marc.espinosa.agendaspring.entidades.Contacto;

import java.util.List;

public interface ContactoRepository {
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(Long id);
    Contacto guardar(Contacto contacto);

    Contacto modificarPorId(Long id, Contacto contacto);

    void eliminar(Long id);
}
