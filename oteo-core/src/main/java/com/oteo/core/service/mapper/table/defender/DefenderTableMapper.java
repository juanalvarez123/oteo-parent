package com.oteo.core.service.mapper.table.defender;

import java.util.List;

import com.oteo.core.service.mapper.model.DefenderCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class DefenderTableMapper implements TableMapper<DefenderCsvFile> {

	@Override
	public DefenderCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return DefenderCsvFile.builder()
				.id_defensorx(dataAsList.get(0))
				.nombre(dataAsList.get(1))
				.edad(dataAsList.get(2))
				.sexo(dataAsList.get(3))
				.genero(dataAsList.get(4))
				.grupo_poblacional(dataAsList.get(5))
				.profesion(dataAsList.get(6))
				.cargo(dataAsList.get(7))
				.premios(dataAsList.get(8))
				.vivx(dataAsList.get(9))
				.fecha_muerte(dataAsList.get(10))
				.observacion(dataAsList.get(11))
				.build();
	}

}
