package com.alura.hotel.test;

import java.time.LocalDate;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.FormaDePago;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

public class HuespedTest {
	public static void main(String[] args) {
		
		HuespedDao Dao = new HuespedDao(new ConnectionFactory().getConnection());
		ReservaDao rDao = new ReservaDao(new ConnectionFactory().getConnection());
		
		LocalDate fechaN = LocalDate.parse("2001-02-05");
		
		LocalDate fecha1 = LocalDate.parse("2022-04-05");
		LocalDate fecha2 = LocalDate.parse("2022-04-12");
		
		Reserva reserva = new Reserva(fecha1, fecha2, FormaDePago.DINERO_EN_EFECTIVO);
		Huesped huesped = new Huesped(
				"Julio",
				"Parada",
				fechaN,
				"Colombiano",
				"88779421"
				);
		
		rDao.ingresar(reserva);
		Dao.ingresar(huesped, reserva);
		System.out.println(huesped.toString());
	}
}
