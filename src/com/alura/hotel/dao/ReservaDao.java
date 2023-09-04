package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

public class ReservaDao {
	
	private Connection connection;
	
	public ReservaDao(Connection con) {
		this.connection = con;
	}
	
	public void ingresar(Reserva reserva) {
		try {
			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO reserva " + 
					"(fecha_entrada, fecha_salida, valor, forma_de_pago) " + 
					"VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			try (statement){
				statement.setObject(1, reserva.getFechaEntrada());
				statement.setObject(2, reserva.getFechaSalida());
				statement.setBigDecimal(  3, reserva.getValor());
				statement.setInt(4, reserva.getFormaDePago().ordinal());
				
				statement.execute();
				
				System.out.println(statement);
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try (resultSet){
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue insertado la reserva con ID: %s", reserva.getId()));
					}
				}

			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificar(Reserva reserva) {
		//TODO
	}
	public void eliminar(int reservaId) {
		//TODO
	}
	public List<Reserva> listar() {
		//TODO
		return new ArrayList<>();
	}
	public List<Reserva> listar(int id) {
		//TODO
		return new ArrayList<>();
	}

}
