package com.oteo.core.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.oteo.core.util.EnvironmentVariable;

@Service
public class DatabaseService {

	public boolean isDatabaseActive() throws SQLException {

		Connection connection = DriverManager.getConnection(
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_URL.name()),
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_USERNAME.name()),
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_PASSWORD.name()));

		return connection != null;
	}

}
