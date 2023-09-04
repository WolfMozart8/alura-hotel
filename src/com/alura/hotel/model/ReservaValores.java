package com.alura.hotel.model;

import java.math.BigDecimal;

public class ReservaValores {

	// Valor igual a un dia es estadía, se multiplica x dias (deferencia entre las fechas)
	public static BigDecimal valorDia = new BigDecimal(70);

	public static BigDecimal getValorDia() {
		return valorDia;
	}

	public static void setValorDia(BigDecimal dia) {
		ReservaValores.valorDia = dia;
	}
	
	
	
}
