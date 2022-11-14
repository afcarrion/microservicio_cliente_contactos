package service;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AccesoServiceImpl implements AccesoService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080";

    @Override
    @Async
    public CompletableFuture<List<Persona>> crearPersonaAsyn(Persona persona){
        System.out.println("Inicia metodo Asyncrono desde el servicio");
        template.postForLocation(url+"/contactos", persona);
        Persona[] personas = template.getForObject(url+"/contactos", Persona[].class);
        System.out.println("Finaliza metodo Asyncrono desde el servicio");
        return CompletableFuture.completedFuture(Arrays.asList(personas));
    }

}
