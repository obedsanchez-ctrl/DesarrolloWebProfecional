package Demo.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HolaController {

    public record Saludo(String mensaje, String mensaje2, String autor, int codigo, boolean activo) {}

    private final List<Saludo> saludos = List.of(
        new Saludo("Que haces", "Hola Querida", "Denver", 1020, true),
        new Saludo("COMO ESTAS", "Soy abel", "Abel", 1021, true)
    );

    // Todos los registros
    @GetMapping(value = "/saludo", produces = "application/json")
    public List<Saludo> listar() {
        return saludos;
    }

    // Uno solo por código
    @GetMapping(value = "/saludo/{codigo}", produces = "application/json")
    public ResponseEntity<Saludo> buscar(@PathVariable int codigo) {
        return saludos.stream()
            .filter(s -> s.codigo() == codigo)
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}