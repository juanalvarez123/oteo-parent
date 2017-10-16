package com.oteo.core.service.internal.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.oteo.core.service.internal.DatabaseService;
import com.oteo.core.util.EnvironmentVariable;

@Service
class DatabaseServiceImpl implements DatabaseService {

	@Override
	public boolean isDatabaseActive() throws SQLException {

		Connection connection = DriverManager.getConnection(
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_URL.name()),
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_USERNAME.name()),
				System.getenv(EnvironmentVariable.SPRING_DATASOURCE_PASSWORD.name()));

		return connection != null;
	}

}
