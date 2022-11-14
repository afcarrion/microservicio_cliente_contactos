package service;

import model.Persona;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AccesoService {

    CompletableFuture<List<Persona>> crearPersonaAsyn(Persona persona);
}
