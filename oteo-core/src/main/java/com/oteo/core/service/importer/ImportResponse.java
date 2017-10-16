package com.oteo.core.service.importer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImportResponse {

	private long recordsAddedOrUpdated;

	private long recordsWithErrors;

	@JsonInclude(Include.NON_NULL)
	private String summary;

}
