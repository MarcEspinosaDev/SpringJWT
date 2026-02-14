package marc.espinosa.agendaspring.servicios.impl;

import marc.espinosa.agendaspring.entidades.Contacto;
import marc.espinosa.agendaspring.repositorios.ContactoRepository;
import marc.espinosa.agendaspring.servicios.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {
    private final ContactoRepository contactoRepository;

    @Autowired
    public ContactoServiceImpl(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepository.obtenerTodos();
    }

    @Override
    public Contacto obtenerPorId(Long id) {
        return contactoRepository.obtenerPorId(id);
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        return contactoRepository.guardar(contacto);
    }

    @Override
    public Contacto modificarPorId(Long id, Contacto contacto) {
        return contactoRepository.modificarPorId(id, contacto);
    }

    @Override
    public void eliminar(Long id) {
        contactoRepository.eliminar(id);
    }
}
