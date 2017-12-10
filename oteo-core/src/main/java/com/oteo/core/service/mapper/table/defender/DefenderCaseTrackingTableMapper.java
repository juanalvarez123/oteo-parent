package com.oteo.core.service.mapper.table.defender;

import java.util.List;

import com.oteo.core.service.mapper.model.DefenderCaseTrackingCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class DefenderCaseTrackingTableMapper implements TableMapper<DefenderCaseTrackingCsvFile> {

	@Override
	public DefenderCaseTrackingCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return DefenderCaseTrackingCsvFile.builder()
				.id_seguimiento(dataAsList.get(0))
				.id_caso(dataAsList.get(1))
				.id_defensorx(dataAsList.get(2))
				.nombre_caso(dataAsList.get(3))
				.avance_caso(dataAsList.get(4))
				.fecha(dataAsList.get(5))
				.observacion(dataAsList.get(6))
				.link(dataAsList.get(7))
				.build();
	}

}
