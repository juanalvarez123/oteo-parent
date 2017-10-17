package com.oteo.core.service.mapper.table.environmental.conflict;

import java.util.List;

import com.oteo.core.service.mapper.model.EnvironmentalConflictCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class EnvironmentalConflictTableMapper
		implements TableMapper<EnvironmentalConflictCsvFile> {

	@Override
	public EnvironmentalConflictCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return EnvironmentalConflictCsvFile.builder()
				.id_conflic(dataAsList.get(0))
				.gmi_cntry(dataAsList.get(1))
				.lugar(dataAsList.get(2))
				.nombre_confl(dataAsList.get(3))
				.latitud(dataAsList.get(4))
				.longitud(dataAsList.get(5))
				.fecha(dataAsList.get(6))
				.conflicto(dataAsList.get(7))
				.extractivismo(dataAsList.get(8))
				.recursos(dataAsList.get(9))
				.fuente(dataAsList.get(10))
				.link(dataAsList.get(11))
				.build();
	}

}
