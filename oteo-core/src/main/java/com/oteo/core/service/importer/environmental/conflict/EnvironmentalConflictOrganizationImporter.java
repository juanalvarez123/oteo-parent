package com.oteo.core.service.importer.environmental.conflict;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.EnvironmentalConflict;
import com.oteo.core.mybatis.domain.EnvironmentalConflictOrganization;
import com.oteo.core.mybatis.domain.Organization;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictOrganizationMapper;
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.EnvironmentalConflictOrganizationCsvFile;

public class EnvironmentalConflictOrganizationImporter implements Importer<EnvironmentalConflictOrganizationCsvFile> {

	private final EnvironmentalConflictMapper environmentalConflictMapper;

	private final OrganizationMapper organizationMapper;

	private final EnvironmentalConflictOrganizationMapper environmentalConflictOrganizationMapper;

	public EnvironmentalConflictOrganizationImporter(EnvironmentalConflictMapper environmentalConflictMapper,
			OrganizationMapper organizationMapper,
			EnvironmentalConflictOrganizationMapper environmentalConflictOrganizationMapper) {

		this.environmentalConflictMapper = environmentalConflictMapper;
		this.organizationMapper = organizationMapper;
		this.environmentalConflictOrganizationMapper = environmentalConflictOrganizationMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, EnvironmentalConflictOrganizationCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, EnvironmentalConflictOrganizationCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			EnvironmentalConflictOrganizationCsvFile environmentalConflictOrganizationCsvFile = entry.getValue();

			if (StringUtils.isBlank(environmentalConflictOrganizationCsvFile.getId_conflicto())) {
				summary.append("Línea " + line + ": id_conflicto no puede ser vacio ni nulo, ");
				continue;
			}

			EnvironmentalConflict environmentalConflict = environmentalConflictMapper
					.getEnvironmentalConflictById(environmentalConflictOrganizationCsvFile.getId_conflicto());

			if (null == environmentalConflict) {
				summary.append("Línea " + line + ": El conflicto ambiental con id_conflicto: "
						+ environmentalConflictOrganizationCsvFile.getId_conflicto() + " no existe, ");
				continue;
			}

			if (StringUtils.isBlank(environmentalConflictOrganizationCsvFile.getId_organizacion())) {
				summary.append("Línea " + line + ": id_organizacion no puede ser vacio ni nulo, ");
				continue;
			}

			Organization organization = organizationMapper
					.getOrganizationById(environmentalConflictOrganizationCsvFile.getId_organizacion());

			if (null == organization) {
				summary.append("Línea " + line + ": La organización con id_organizacion: "
						+ environmentalConflictOrganizationCsvFile.getId_organizacion() + " no existe, ");
				continue;
			}

			EnvironmentalConflictOrganization environmentalConflictOrganization = transformEnvironmentalConflictOrganization(
					environmentalConflictOrganizationCsvFile);

			try {
				environmentalConflictOrganizationMapper
						.addOrUpdateEnvironmentalConflictOrganization(environmentalConflictOrganization);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private EnvironmentalConflictOrganization transformEnvironmentalConflictOrganization(
			final EnvironmentalConflictOrganizationCsvFile environmentalConflictOrganizationCsvFile) {

		return EnvironmentalConflictOrganization.builder()
				.environmentalConflictId(environmentalConflictOrganizationCsvFile.getId_conflicto())
				.organizationId(environmentalConflictOrganizationCsvFile.getId_organizacion())
				.build();
	}

}
