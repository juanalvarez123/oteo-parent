package com.oteo.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonPropertyOrder({ "errorMessage" })
public class ApiError {

	private String errorMessage;

}
