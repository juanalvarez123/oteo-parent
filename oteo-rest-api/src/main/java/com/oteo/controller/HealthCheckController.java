package com.oteo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oteo.core.mybatis.mapper.EnvironmentMapper;
import com.oteo.core.service.DatabaseService;
import com.oteo.model.response.ApiGenericResponse;

@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
@RestController
public class HealthCheckController {

	private final DatabaseService databaseService;

	private final EnvironmentMapper environmentMapper;

	@Autowired
	public HealthCheckController(DatabaseService databaseService, EnvironmentMapper environmentMapper) {

		this.databaseService = databaseService;
		this.environmentMapper = environmentMapper;
	}

	@RequestMapping(path = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiGenericResponse getHealthCheck() throws SQLException {

		boolean isDatabaseStatusActive = databaseService.isDatabaseActive();

		return ApiGenericResponse.builder()
				.message("Welcome Angie, you are in " + environmentMapper.getEnvironment().getName()
						+ " environment")
				.databaseStatus(isDatabaseStatusActive)
				.build();
	}

}
