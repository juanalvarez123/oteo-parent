package com.oteo.core.service.mapper.table.organization;

import java.util.List;

import com.oteo.core.service.mapper.model.ActivityOrganizationCsvFile;
import com.oteo.core.service.mapper.table.TableMapper;

public class ActivityOrganizationTableMapper implements TableMapper<ActivityOrganizationCsvFile> {

	@Override
	public ActivityOrganizationCsvFile map(final String line) {

		List<String> dataAsList = cleanRow(line);

		return ActivityOrganizationCsvFile.builder()
				.id_actividad(dataAsList.get(0))
				.id_organizacion(dataAsList.get(1))
				.tipo_resistencia(dataAsList.get(2))
				.descrip_resistencias(dataAsList.get(3))
				.materiales_produc(dataAsList.get(4))
				.nombre_material(dataAsList.get(5))
				.fecha(dataAsList.get(6))
				.link(dataAsList.get(7))
				.build();
	}

}
