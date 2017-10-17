package com.oteo.core.service.mapper.table.company;

import java.util.List;

import com.oteo.core.service.mapper.model.CompanyCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class CompanyTableMapper implements TableMapper<CompanyCsvFile> {

	@Override
	public CompanyCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return CompanyCsvFile.builder()
				.id_empresa(dataAsList.get(0))
				.nombre_empresa(dataAsList.get(1))
				.tipo_empresa(dataAsList.get(2))
				.pais_empresa(dataAsList.get(3))
				.participacion_empresa(dataAsList.get(4))
				.descripcion(dataAsList.get(5))
				.link(dataAsList.get(6))
				.build();
	}

}
