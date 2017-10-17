package com.oteo.core.service.mapper.table.environmental.conflict;

import java.util.List;

import com.oteo.core.service.mapper.model.EnvironmentalConflictOrganizationCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class EnvironmentalConflictOrganizationTableMapper
		implements TableMapper<EnvironmentalConflictOrganizationCsvFile> {

	@Override
	public EnvironmentalConflictOrganizationCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return EnvironmentalConflictOrganizationCsvFile.builder()
				.id_conflicto(dataAsList.get(0))
				.id_organizacion(dataAsList.get(1))
				.build();
	}

}
