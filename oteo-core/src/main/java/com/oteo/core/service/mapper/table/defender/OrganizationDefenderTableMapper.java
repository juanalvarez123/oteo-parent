package com.oteo.core.service.mapper.table.defender;

import java.util.List;

import com.oteo.core.service.mapper.model.OrganizationDefenderCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class OrganizationDefenderTableMapper
		implements TableMapper<OrganizationDefenderCsvFile> {

	@Override
	public OrganizationDefenderCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return OrganizationDefenderCsvFile.builder()
				.id_defensorx(dataAsList.get(0))
				.id_organizacion(dataAsList.get(1))
				.build();
	}

}
