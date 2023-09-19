package com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		// cambiar por los datos correctos de tu base de datos
		String url = "jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
		String user = "root";
		String password = "root1234";
		
		
		var poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl(url);
		poolDataSource.setUser(user);
		poolDataSource.setPassword(password);
		
		poolDataSource.setMaxPoolSize(10);
		
		this.dataSource = poolDataSource;
	}
	
	public Connection getConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
