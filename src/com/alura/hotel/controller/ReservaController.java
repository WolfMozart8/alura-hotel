package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Reserva;

public class ReservaController {

	private ReservaDao reservaDao;
	
	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDao = new ReservaDao(factory.getConnection());
	}
	
	public void insertar(Reserva reserva) {
		reservaDao.ingresar(reserva);
	}
	public void modificar(Reserva reserva) {
		reservaDao.modificar(reserva);
	}
	public int eliminar(int reservaId) {
		return reservaDao.eliminar(reservaId);
	}
	public List<Reserva> listar() {
		return reservaDao.listar();
	}
	public List<Reserva> buscar(String text) {
		return reservaDao.buscar(text);
	}
}
