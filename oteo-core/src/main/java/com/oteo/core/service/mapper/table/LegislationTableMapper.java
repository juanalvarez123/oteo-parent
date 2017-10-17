package com.oteo.core.service.mapper.table;

import java.util.List;

import com.oteo.core.service.mapper.model.LegislationCsvFile;

public class LegislationTableMapper implements TableMapper<LegislationCsvFile> {

	@Override
	public LegislationCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return LegislationCsvFile.builder()
				.id_legis(dataAsList.get(0))
				.pais(dataAsList.get(1))
				.fecha(dataAsList.get(2))
				.organismo(dataAsList.get(3))
				.tipo_legislacion(dataAsList.get(4))
				.nombre_legislacion(dataAsList.get(5))
				.observaciones(dataAsList.get(6))
				.link(dataAsList.get(7))
				.componente(dataAsList.get(8))
				.build();
	}

}
