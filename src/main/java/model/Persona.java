package model;

public class Persona {

    private Integer id;
    private String nombre;
    private String email;
    private Integer edad;

    public Persona(Integer id,String nombre, String email, Integer edad) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }
    public Persona(){
        super();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
