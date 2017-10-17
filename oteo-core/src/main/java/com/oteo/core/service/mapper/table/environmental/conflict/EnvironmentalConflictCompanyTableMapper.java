package com.oteo.core.service.mapper.table.environmental.conflict;

import java.util.List;

import com.oteo.core.service.mapper.model.EnvironmentalConflictCompanyCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class EnvironmentalConflictCompanyTableMapper
		implements TableMapper<EnvironmentalConflictCompanyCsvFile> {

	@Override
	public EnvironmentalConflictCompanyCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return EnvironmentalConflictCompanyCsvFile.builder()
				.id_conflicto(dataAsList.get(0))
				.id_empresa(dataAsList.get(1))
				.build();
	}

}
