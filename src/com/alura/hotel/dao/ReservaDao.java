package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.FormaDePago;
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
	
	public int modificar(Reserva reserva) {
		//TODO
		final Connection con = new ConnectionFactory().getConnection();
		
		try (con){
			String sql = "UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, valor = ? WHERE id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try (statement){
				statement.setObject(1, reserva.getFechaEntrada());
				statement.setObject(2, reserva.getFechaSalida());
				statement.setBigDecimal(3, reserva.getValor());
				statement.setInt(4, reserva.getId());
				
				statement.execute();
				
				int updatedCount = statement.getUpdateCount();
				
				return updatedCount;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
				throw new RuntimeException(e);	
		}
		
	}
	public int eliminar(int reservaId) {
		//TODO
		final Connection con = new ConnectionFactory().getConnection();
		
		try (con){
			final PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM reserva WHERE id = ?"
					);
			
			try (statement) {
				statement.setInt(1, reservaId);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public List<Reserva> listar() {
		//TODO
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = connection.prepareStatement(
					"SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reserva");
			try (statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet) {
					while(resultSet.next()) {
						FormaDePago formaDePago = FormaDePago.obtenerPorIndice(resultSet.getInt("forma_de_pago"));
						
						Reserva reserva = new Reserva(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada").toLocalDate(),
								resultSet.getDate("fecha_salida").toLocalDate(),
								resultSet.getBigDecimal("valor"),
								formaDePago
								);
						
						resultado.add(reserva);
					}
				}
				
			}
//			connection.close();
			return resultado;
					
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	public List<Reserva> buscar(String texto) {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM reserva "
					+ "WHERE fecha_entrada LIKE '%" + texto + "%' "
					+ "OR fecha_salida LIKE '%" + texto + "%' "
					+ "OR forma_de_pago LIKE '%" + texto + "%' ";
			final PreparedStatement statement = connection.prepareStatement(sql);
			try (statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet) {
					while(resultSet.next()) {
						FormaDePago formaDePago = FormaDePago.obtenerPorIndice(resultSet.getInt("forma_de_pago"));

						Reserva reserva = new Reserva(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada").toLocalDate(),
								resultSet.getDate("fecha_salida").toLocalDate(),
								resultSet.getBigDecimal("valor"),
								formaDePago
								);
						
						resultado.add(reserva);
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

}
