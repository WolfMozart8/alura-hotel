package com.alura.hotel.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.FormaDePago;
import com.alura.hotel.model.Reserva;

public class ReservaTest {
	public static void main(String[] args) {
		
		ReservaDao rDAO = new ReservaDao(new ConnectionFactory().getConnection());
		
		LocalDate inicioFecha = LocalDate.parse("2020-04-11");
		LocalDate finalFecha = LocalDate.parse("2020-04-16");
		
		int diferencia = inicioFecha.until(finalFecha).getDays();
		Long choroTIme = ChronoUnit.DAYS.between(inicioFecha, finalFecha);
		long tarifa = choroTIme * 230;
		
		System.out.println(tarifa);
		
		Reserva reserva = new Reserva(1, inicioFecha, finalFecha, new BigDecimal(tarifa), FormaDePago.TARJETA_DE_DEBITO);
		Reserva r2 = new Reserva(inicioFecha, finalFecha, FormaDePago.TARJETA_DE_CREDITO);
		
		System.out.println(r2.toString());
		System.out.println(FormaDePago.DINERO_EN_EFECTIVO);
		
		rDAO.ingresar(r2);
		
	}
}
