package model;

public class Sala {
	
    private int id;
    private String nombre;
    private int capacidad;
    private String recursos;
    
    public Sala(int id, String nombre, int capacidad, String recursos) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.recursos = recursos;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getRecursos() { return recursos; }
    public void setRecursos(String recursos) { this.recursos = recursos; }

}