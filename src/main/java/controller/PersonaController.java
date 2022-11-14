package controller;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonaController {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:8080";

    @GetMapping(value="/persona/{id}/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> crearPersona(@PathVariable("id") Integer id,
                                      @PathVariable("nombre") String nombre,
                                      @PathVariable("email") String email,
                                      @PathVariable("edad") Integer edad){
        Persona persona = new Persona(id,nombre, email, edad);
        restTemplate.postForLocation(url+"/contactos", persona);
        Persona[] personas = restTemplate.getForObject(url+"/contactos", Persona[].class);
        return Arrays.asList(personas);
    }

    @GetMapping(value="/persona/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarXEdades(@PathVariable("edad1") int edad1,
                                       @PathVariable("edad2") int edad2){
        Persona[] personas = restTemplate.getForObject(url+"/contactos", Persona[].class);
        return Arrays.stream(personas)
                .filter(p->p.getEdad()>=edad1 && p.getEdad()<=edad2)
                .collect(Collectors.toList());
    }
}
