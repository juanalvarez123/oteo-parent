package com.oteo.core.service.mapper.table;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.service.mapper.model.CompanyCsvFile;

public class CompanyTableMapper implements TableMapper<CompanyCsvFile> {

	@Override
	public CompanyCsvFile map(final String line) {

		String[] dataAsArray = line.split(";");
		List<String> dataAsList = Arrays.asList(dataAsArray);
		dataAsList.forEach(field -> {
			if (StringUtils.isBlank(field)) {
				field = null;
			}
		});

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
