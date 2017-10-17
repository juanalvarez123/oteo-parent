package com.oteo.core.service.mapper.table;

import java.util.List;

import com.oteo.core.service.mapper.model.HumanRightViolationCsvFile;

public class HumanRightViolationTableMapper implements TableMapper<HumanRightViolationCsvFile> {

	@Override
	public HumanRightViolationCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return HumanRightViolationCsvFile.builder()
				.id_vulneracion(dataAsList.get(0))
				.id_defensorx(dataAsList.get(1))
				.agresion(dataAsList.get(2))
				.actor_victimizante(dataAsList.get(3))
				.fecha(dataAsList.get(4))
				.observaciones(dataAsList.get(5))
				.link(dataAsList.get(6))
				.build();
	}

}
