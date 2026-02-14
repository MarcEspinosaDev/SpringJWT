package marc.espinosa.agendaspring.controladores;

import marc.espinosa.agendaspring.entidades.Contacto;
import marc.espinosa.agendaspring.servicios.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
public class ContactoController {
    private final ContactoService cs;

    @Autowired
    public ContactoController(ContactoService cs) {
        this.cs = cs;
    }

    @GetMapping
    public List<Contacto> obtenerTodos(){
        return cs.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Contacto obtenerPorId(@PathVariable Long id){
        return cs.obtenerPorId(id);
    }

    @PostMapping
    public Contacto guardar(@RequestBody Contacto contacto){
        return cs.guardar(contacto);
    }

    @PutMapping("/{id}")
    public Contacto modificarPorId(@PathVariable Long id, @RequestBody Contacto contacto){
        return cs.modificarPorId(id, contacto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable  Long id){
        cs.eliminar(id);
    }
}
