package com.oteo.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "httpStatus", "message", "databaseStatus" })
public class ApiGenericResponse {

	private int httpStatus;

	private String message;

	private boolean databaseStatus;

}
