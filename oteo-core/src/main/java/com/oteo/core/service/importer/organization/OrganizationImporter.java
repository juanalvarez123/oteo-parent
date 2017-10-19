package com.oteo.core.service.importer.organization;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Organization;
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.OrganizationCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class OrganizationImporter implements Importer<OrganizationCsvFile> {

	private final OrganizationMapper organizationMapper;

	public OrganizationImporter(OrganizationMapper organizationMapper) {

		this.organizationMapper = organizationMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, OrganizationCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, OrganizationCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			OrganizationCsvFile organizationCsvFile = entry.getValue();

			if (StringUtils.isBlank(organizationCsvFile.getId_organizacion())) {
				summary.append("Línea " + line + ": id_organizacion no puede ser vacio ni nulo, ");
				continue;
			}

			Organization organization = transformOrganization(organizationCsvFile);

			try {
				organizationMapper.addOrUpdateOrganization(organization);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private Organization transformOrganization(final OrganizationCsvFile organizationCsvFile) {

		return Organization.builder()
				.organizationId(organizationCsvFile.getId_organizacion())
				.name(organizationCsvFile.getNombre_organizacion())
				.country(organizationCsvFile.getPais())
				.place(organizationCsvFile.getLugar())
				.organizationClass(organizationCsvFile.getClase_organizacion())
				.type(organizationCsvFile.getTipo_organ())
				.goal(organizationCsvFile.getObjetivo())
				.link(organizationCsvFile.getLink_organizacion())
				.active(OteoUtil.getBooleanFromString(organizationCsvFile.getActiva()))
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
