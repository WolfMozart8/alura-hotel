package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

public class HuespedController {

	private HuespedDao huespedDao;
	
	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDao = new HuespedDao(factory.getConnection());
	}
	
	public void insertar(Huesped huesped, Reserva reserva) {
		huespedDao.ingresar(huesped, reserva);
	}
	public int modificar(Huesped huesped) {
		return huespedDao.modificar(huesped);
	}
	public int eliminar(int huespedId) {
		return huespedDao.eliminar(huespedId);
	}
	public List<Huesped> listar(){
		return huespedDao.listar();
	}
	public List<Huesped> buscar(String texto) {
		return huespedDao.buscar(texto);
	}
}
