package com.alura.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.dao.HuespedDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Huesped;

public class ReservaController {

	private HuespedDao huespedDao;
	
	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDao = new HuespedDao(factory.getConnection());
	}
	
	public void insertar() {
		//TODO
	}
	public void modificar() {
		//TODO
	}
	public void eliminar() {
		//TODO
	}
	public List<Huesped> listar() {
		//TODO
		return new ArrayList<>();
	}
}
