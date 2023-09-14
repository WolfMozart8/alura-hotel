package com.alura.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservaValores {

	// Valor igual a un dia es estadía, se multiplica x dias (deferencia entre las fechas)
	public static BigDecimal valorDia = new BigDecimal(70);

	public static BigDecimal getValorDia() {
		return valorDia;
	}

	public static void setValorDia(BigDecimal dia) {
		ReservaValores.valorDia = dia;
	}
	
	public static BigDecimal calcularValor (LocalDate fecha1, LocalDate fecha2) {
		Long diasDiferencia = ChronoUnit.DAYS.between(fecha1, fecha2);
		BigDecimal valorTotal = new BigDecimal(diasDiferencia).multiply(valorDia);
		return valorTotal;
	}
	
	
	
}
