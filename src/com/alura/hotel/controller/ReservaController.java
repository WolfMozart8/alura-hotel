package com.alura.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

public class ReservaController {

	private ReservaDao reservaDao;
	
	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDao = new ReservaDao(factory.getConnection());
	}
	
	public void insertar(Reserva reserva) {
		//TODO
		reservaDao.ingresar(reserva);
	}
	public void modificar(Reserva reserva) {
		//TODO
		reservaDao.modificar(reserva);
	}
	public int eliminar(int reservaId) {
		//TODO
		return reservaDao.eliminar(reservaId);
	}
	public List<Reserva> listar() {
		//TODO
		return reservaDao.listar();
	}
	public List<Reserva> buscar(String text) {
		//TODO
		return reservaDao.buscar(text);
	}
}
