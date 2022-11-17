package controller;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import service.AccesoService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
public class PersonaController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccesoService accesoService;

    String url = "http://localhost:8080";

    //Async calls to other microservices
    @GetMapping(value="/personaAsync/{id}/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> crearPersonaAsync(@PathVariable("id") Integer id,
                                      @PathVariable("nombre") String nombre,
                                      @PathVariable("email") String email,
                                      @PathVariable("edad") Integer edad){
        Persona persona = new Persona(id,nombre, email, edad);
        CompletableFuture<List<Persona>> resultado = accesoService.crearPersonaAsyn(persona);
        for(int i=1;i<50;i++){
            System.out.println("Metodo del controlador....esperando");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("********Final Metodo del controlador....esperando*******");
            return resultado.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value="/persona/{id}/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> crearPersona(@PathVariable("id") Integer id,
                                      @PathVariable("nombre") String nombre,
                                      @PathVariable("email") String email,
                                      @PathVariable("edad") Integer edad){
        Persona persona = new Persona(id,nombre, email, edad);
        try {
            ResponseEntity<Void> respuesta = restTemplate.postForEntity(url+"/contactos", persona, Void.class);
            HttpHeaders headers = respuesta.getHeaders();
            int total = Integer.parseInt(headers.get("total").get(0));
            if(total == 0){
                return null;
            }
            ResponseEntity<Persona[]> personas = restTemplate.getForEntity(url+"/contactos", Persona[].class);
            return Arrays.asList(personas.getBody());
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping(value="/persona/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarXEdades(@PathVariable("edad1") int edad1,
                                       @PathVariable("edad2") int edad2){
        ResponseEntity<Persona[]> personas = restTemplate.getForEntity(url+"/contactos", Persona[].class);
        return Arrays.stream(personas.getBody())
                .filter(p->p.getEdad()>=edad1 && p.getEdad()<=edad2)
                .collect(Collectors.toList());
    }
}
