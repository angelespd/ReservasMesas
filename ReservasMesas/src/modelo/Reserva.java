package modelo;

import java.util.Date;

public class Reserva {
	private String nombre;
	private String apellido;
	private int comensales;
	private int idReserva;
	private Date fecha;
	private Mesa mesaReservada;
	
	public Reserva(String nombre, String apellido, int comensales, int idReserva, Date fecha, Mesa mesaReservada) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.comensales = comensales;
		this.idReserva = idReserva;
		this.fecha = fecha;
		this.mesaReservada = mesaReservada;
	}
	public Reserva() {
		
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
	public int getComensales() {
		return comensales;
	}
	public void setComensales(int comensales) {
		this.comensales = comensales;
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Mesa getMesaReservada() {
		return mesaReservada;
	}
	public void setMesaReservada(Mesa mesaReservada) {
		this.mesaReservada = mesaReservada;
	}
	
}
