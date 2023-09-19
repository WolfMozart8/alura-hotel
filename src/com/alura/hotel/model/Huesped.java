package com.alura.hotel.model;

import java.sql.Date;
import java.time.LocalDate;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	
	private Integer reservaId;	
	
	public Integer getReservaId() {
		return reservaId;
	}
	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}
	public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	
	
	public Huesped(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer reservaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public Date getFechaNacimientoDate() {
		return Date.valueOf(fechaNacimiento);
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Huesped [id=" + id + "nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", reservaId="
				+ reservaId + "]";
	}
	
	
	

}