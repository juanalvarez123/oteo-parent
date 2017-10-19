package com.oteo.core.service.importer.defender;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Defender;
import com.oteo.core.mybatis.domain.Organization;
import com.oteo.core.mybatis.domain.OrganizationDefender;
import com.oteo.core.mybatis.mapper.DefenderMapper;
import com.oteo.core.mybatis.mapper.OrganizationDefenderMapper;
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.OrganizationDefenderCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class OrganizationDefenderImporter implements Importer<OrganizationDefenderCsvFile> {

	private final DefenderMapper defenderMapper;

	private final OrganizationMapper organizationMapper;

	private final OrganizationDefenderMapper organizationDefenderMapper;

	public OrganizationDefenderImporter(DefenderMapper defenderMapper, OrganizationMapper organizationMapper,
			OrganizationDefenderMapper organizationDefenderMapper) {

		this.defenderMapper = defenderMapper;
		this.organizationMapper = organizationMapper;
		this.organizationDefenderMapper = organizationDefenderMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, OrganizationDefenderCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, OrganizationDefenderCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			OrganizationDefenderCsvFile organizationDefenderCsvFile = entry.getValue();

			if (StringUtils.isBlank(organizationDefenderCsvFile.getId_defensorx())) {
				summary.append("Línea " + line + ": id_defensorx no puede ser vacio ni nulo, ");
				continue;
			}

			Defender defender = defenderMapper.getDefenderById(organizationDefenderCsvFile.getId_defensorx());

			if (null == defender) {
				summary.append("Línea " + line + ": El defensor con id_defensorx: "
						+ organizationDefenderCsvFile.getId_defensorx() + " no existe, ");
				continue;
			}

			if (StringUtils.isBlank(organizationDefenderCsvFile.getId_organizacion())) {
				summary.append("Línea " + line + ": id_organizacion no puede ser vacio ni nulo, ");
				continue;
			}

			Organization organization = organizationMapper
					.getOrganizationById(organizationDefenderCsvFile.getId_organizacion());

			if (null == organization) {
				summary.append("Línea " + line + ": La organización con id_organizacion: "
						+ organizationDefenderCsvFile.getId_organizacion() + " no existe, ");
				continue;
			}

			OrganizationDefender organizationDefender = transformOrganizationDefender(
					organizationDefenderCsvFile);

			try {
				organizationDefenderMapper.addOrUpdateOrganizationDefender(organizationDefender);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private OrganizationDefender transformOrganizationDefender(
			final OrganizationDefenderCsvFile organizationDefenderCsvFile) {

		return OrganizationDefender.builder()
				.defenderId(organizationDefenderCsvFile.getId_defensorx())
				.organizationId(organizationDefenderCsvFile.getId_organizacion())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
