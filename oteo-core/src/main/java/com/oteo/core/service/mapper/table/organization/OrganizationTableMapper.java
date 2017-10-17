package com.oteo.core.service.mapper.table.organization;

import java.util.List;

import com.oteo.core.service.mapper.model.OrganizationCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class OrganizationTableMapper implements TableMapper<OrganizationCsvFile> {

	@Override
	public OrganizationCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return OrganizationCsvFile.builder()
				.id_organizacion(dataAsList.get(0))
				.nombre_organizacion(dataAsList.get(1))
				.pais(dataAsList.get(2))
				.lugar(dataAsList.get(3))
				.clase_organizacion(dataAsList.get(4))
				.tipo_organ(dataAsList.get(5))
				.objetivo(dataAsList.get(6))
				.link_organizacion(dataAsList.get(7))
				.activa(dataAsList.get(8))
				.build();
	}

}
