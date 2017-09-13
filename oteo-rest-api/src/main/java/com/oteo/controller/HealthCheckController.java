package com.oteo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthCheckController {

	@RequestMapping(path = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiGenericResponse getHealthCheck() {

		return new ApiGenericResponse("Welcome Juan Sebastian !!");
	}

}
