package marc.espinosa.agendaspring.repositorios.impl;

import marc.espinosa.agendaspring.entidades.Contacto;
import marc.espinosa.agendaspring.repositorios.ContactoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ContactoRepositoryImpl implements ContactoRepository {
    private final Map<Long, Contacto> contactos = new HashMap<>();
    private Long idSecuencia = 1L;

    public ContactoRepositoryImpl(){
        Contacto contactoInicial = new Contacto("Calos","654765876");
        contactoInicial.setId(idSecuencia++);
        contactos.put(contactoInicial.getId(), contactoInicial);
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos.values());
    }

    @Override
    public Contacto obtenerPorId(Long id) {
        return contactos.get(id);
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        if (contacto.getId() == null) {
            contacto.setId(idSecuencia++);
        }
        return contacto;
    }

    @Override
    public Contacto modificarPorId(Long id, Contacto contacto) {
        Contacto contactoExistente = contactos.get(id);
        if (contactoExistente == null) {
            return null;
        }
        contactoExistente.setTelefono(contacto.getTelefono());
        return contactoExistente;
    }

    @Override
    public void eliminar(Long id) {
        contactos.remove(id);
    }
}
