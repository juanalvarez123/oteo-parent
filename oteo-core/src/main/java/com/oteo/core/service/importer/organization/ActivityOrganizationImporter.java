package com.oteo.core.service.importer.organization;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.ActivityOrganization;
import com.oteo.core.mybatis.domain.Organization;
import com.oteo.core.mybatis.mapper.ActivityOrganizationMapper;
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.ActivityOrganizationCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class ActivityOrganizationImporter implements Importer<ActivityOrganizationCsvFile> {

	private final ActivityOrganizationMapper activityOrganizationMapper;

	private final OrganizationMapper organizationMapper;

	public ActivityOrganizationImporter(ActivityOrganizationMapper activityOrganizationMapper,
			OrganizationMapper organizationMapper) {

		this.activityOrganizationMapper = activityOrganizationMapper;
		this.organizationMapper = organizationMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, ActivityOrganizationCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, ActivityOrganizationCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			ActivityOrganizationCsvFile activityOrganizationCsvFile = entry.getValue();

			if (StringUtils.isBlank(activityOrganizationCsvFile.getId_actividad())) {
				summary.append("Línea " + line + ": id_actividad no puede ser vacio ni nulo, ");
				continue;
			}

			if (StringUtils.isNotBlank(activityOrganizationCsvFile.getId_organizacion())) {
				Organization organization = organizationMapper
						.getOrganizationById(activityOrganizationCsvFile.getId_organizacion());

				if (null == organization) {
					summary.append("Línea " + line + ": La organización con id_organizacion: "
							+ activityOrganizationCsvFile.getId_organizacion() + " no existe, ");
					continue;
				}
			}

			ActivityOrganization activityOrganization = transformActivityOrganization(activityOrganizationCsvFile);

			try {
				activityOrganizationMapper.addOrUpdateActivityOrganization(activityOrganization);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private ActivityOrganization transformActivityOrganization(
			final ActivityOrganizationCsvFile activityOrganizationCsvFile) {

		return ActivityOrganization.builder()
				.activityOrganizationId(activityOrganizationCsvFile.getId_actividad())
				.organizationId(StringUtils.isNotBlank(activityOrganizationCsvFile.getId_organizacion()) ?
					activityOrganizationCsvFile.getId_organizacion() : null)
				.strengthType(activityOrganizationCsvFile.getTipo_resistencia())
				.strengthDescription(activityOrganizationCsvFile.getDescrip_resistencias())
				.results(activityOrganizationCsvFile.getMateriales_produc())
				.resultName(activityOrganizationCsvFile.getNombre_material())
				.datetime(OteoUtil.getLocalDateFromString(activityOrganizationCsvFile.getFecha()))
				.link(activityOrganizationCsvFile.getLink())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
