package com.oteo.core.service.importer;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public interface Importer<T> {

	long iterateMap(final Map<Integer, T> records, StringBuilder summary);

	default ImportResponse importRecords(Map<Integer, T> records) {

		StringBuilder summary = new StringBuilder();

		long recordsAddedOrUpdated = iterateMap(records, summary);

		return ImportResponse.builder()
				.recordsAddedOrUpdated(recordsAddedOrUpdated)
				.recordsWithErrors(records.values().size() - recordsAddedOrUpdated)
				.summary(StringUtils.isNotBlank(summary) ? summary.toString() : null)
				.build();
	}

}
