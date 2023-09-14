package com.alura.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

	private Integer id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private BigDecimal valor;
	private FormaDePago formaDePago;
	

	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaDePago) {
		
		this.valor = diasValor(fechaEntrada, fechaSalida);
		
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaDePago;
	}
	
	public Reserva(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal valor,
			FormaDePago formaDePago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	public Reserva(int id, LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal valor,
			int formaDePago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = FormaDePago.obtenerPorIndice(formaDePago);
	}
	
	public Reserva(LocalDate fecha_entrada, LocalDate fecha_salida) {
		// TODO Auto-generated constructor stub
		this.fechaEntrada = fecha_entrada;
		this.fechaSalida = fecha_salida;
		this.valor = diasValor(fecha_entrada, fecha_salida);
	}

	public BigDecimal diasValor (LocalDate fechaIn, LocalDate fechaOut) {
		Long diasDiferencia = ChronoUnit.DAYS.between(fechaIn, fechaOut);
		BigDecimal valorTotal = new BigDecimal(diasDiferencia).multiply(ReservaValores.getValorDia());
		return valorTotal;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: " + this.id + ", ini: " + fechaEntrada + ", fin: " + fechaSalida + ", valor: " + valor + ", pago: " + formaDePago;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
}