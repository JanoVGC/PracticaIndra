package model;

public class Trabajadores {
	
	 private int id;
	 private String nombre;
	 private String email;
	 private String departamento;
	 
	 public Trabajadores(int id, String nombre, String email, String departamento) {
	        this.id = id;
	        this.nombre = nombre;
	        this.email = email;
	        this.departamento = departamento;
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


}
