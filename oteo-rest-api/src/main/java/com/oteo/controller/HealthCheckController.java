package com.oteo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oteo.core.service.DatabaseService;

@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
@RestController
public class HealthCheckController {

	private final DatabaseService databaseService;

	@Autowired
	public HealthCheckController(DatabaseService databaseService) {

		this.databaseService = databaseService;
	}

	@RequestMapping(path = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiGenericResponse getHealthCheck() throws SQLException {

		boolean isDatabaseStatusActive = databaseService.isDatabaseActive();

		return ApiGenericResponse.builder()
				.httpStatus(HttpStatus.OK.value())
				.message("Welcome Juan Sebastian !!!")
				.databaseStatus(isDatabaseStatusActive)
				.build();
	}

}
