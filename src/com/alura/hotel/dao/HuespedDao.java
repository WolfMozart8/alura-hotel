package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.FormaDePago;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

public class HuespedDao {
	
	private Connection connection;
	
	public HuespedDao(Connection con) {
		this.connection = con;
	}
	public HuespedDao() {
		ConnectionFactory factory = new ConnectionFactory();
		this.connection = 	factory.getConnection();	

	}
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
			
//			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int modificar(Huesped huesped) {
		//TODO
		final Connection con = new ConnectionFactory().getConnection();
		
		try (con){
			String sql = "UPDATE huesped SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try (statement){
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(  3, huesped.getFechaNacimientoDate());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(   6, huesped.getId());
				
				statement.execute();
				
				int updatedCount = statement.getUpdateCount();
				
				return updatedCount;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
				throw new RuntimeException(e);	
		}
		
	}
	public int eliminar(int huespedId) {
		//TODO
		final Connection con = new ConnectionFactory().getConnection();
		
		try (con){
			final PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM huesped WHERE id = ?"
					);
			
			try (statement) {
				statement.setInt(1, huespedId);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = connection.prepareStatement(
					"SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, reserva_id from huesped");
			try (statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet) {
					while(resultSet.next()) {
						
						Huesped huesped = new Huesped(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_de_nacimiento").toLocalDate(),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"),
								resultSet.getInt("reserva_id")
								);
						
						resultado.add(huesped);
					}
				}
				
			}
//			connection.close();
					
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}
	public List<Huesped> buscar(String texto) {
		List<Huesped> resultado = new ArrayList<>();

		
		try {
			String sql = "SELECT * FROM huesped "
					+ "WHERE nombre LIKE '%" + texto + "%' "
					+ "OR apellido LIKE '%" + texto + "%' "
					+ "OR fecha_de_nacimiento LIKE '%" + texto + "%' "
					+ "OR telefono LIKE '%" + texto + "%' "
					+ "OR nacionalidad LIKE '%" + texto + "%' "
					;
			final PreparedStatement statement = connection.prepareStatement(sql);
			try (statement) {
				statement.execute();
				
				
				final ResultSet resultSet = statement.getResultSet();
				try (resultSet){
					while (resultSet.next()) {
						
						Huesped huesped = new Huesped(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_de_nacimiento").toLocalDate(),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"),
								resultSet.getInt("reserva_id")
								);
						
						resultado.add(huesped);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

}
