package com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		
		var poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		poolDataSource.setUser("root");
		poolDataSource.setPassword("root1234");
		
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
