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

public class HuespedDao {
	
	private Connection connection;
	
	public HuespedDao(Connection con) {
		this.connection = con;
	}
	//TODO crear conexion con reserva id
	public void ingresar(Huesped huesped, Reserva reserva) {
		try {
			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO huesped " + 
					"(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, reserva_id) " + 
					"VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			try (statement){
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(  3, huesped.getFechaNacimientoDate());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, reserva.getId());

				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try (resultSet){
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						huesped.setReservaId(reserva.getId());
						System.out.println(String.format("Fue insertado el huesped: %s", huesped.toString()));
					}
				}

			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificar(Huesped huesped) {
		//TODO
	}
	public void eliminar(int huespedId) {
		//TODO
	}
	public List<Huesped> listar() {
		//TODO
		return new ArrayList<>();
	}
	public List<Huesped> listar(int id) {
		//TODO
		return new ArrayList<>();
	}

}
