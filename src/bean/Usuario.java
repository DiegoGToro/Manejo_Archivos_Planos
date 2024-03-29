package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String Nombre;
	private Long Salario;
	private LocalDate fechaNacimiento;

	public Usuario() {
		id = 0;
		Nombre = "";
		Salario = 0l;
		fechaNacimiento = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Long getSalario() {
		return Salario;
	}

	public void setSalario(Long salario) {
		Salario = salario;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}