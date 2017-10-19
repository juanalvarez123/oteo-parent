package com.oteo.core.service.importer.environmental.conflict;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Company;
import com.oteo.core.mybatis.domain.EnvironmentalConflict;
import com.oteo.core.mybatis.domain.EnvironmentalConflictCompany;
import com.oteo.core.mybatis.mapper.CompanyMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictCompanyMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.EnvironmentalConflictCompanyCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class EnvironmentalConflictCompanyImporter implements Importer<EnvironmentalConflictCompanyCsvFile> {

	private final EnvironmentalConflictMapper environmentalConflictMapper;

	private final CompanyMapper companyMapper;

	private final EnvironmentalConflictCompanyMapper environmentalConflictCompanyMapper;

	public EnvironmentalConflictCompanyImporter(EnvironmentalConflictMapper environmentalConflictMapper,
			CompanyMapper companyMapper,
			EnvironmentalConflictCompanyMapper environmentalConflictCompanyMapper) {

		this.environmentalConflictMapper = environmentalConflictMapper;
		this.companyMapper = companyMapper;
		this.environmentalConflictCompanyMapper = environmentalConflictCompanyMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, EnvironmentalConflictCompanyCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, EnvironmentalConflictCompanyCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			EnvironmentalConflictCompanyCsvFile environmentalConflictCompanyCsvFile = entry.getValue();

			if (StringUtils.isBlank(environmentalConflictCompanyCsvFile.getId_conflicto())) {
				summary.append("Línea " + line + ": id_conflicto no puede ser vacio ni nulo, ");
				continue;
			}

			EnvironmentalConflict environmentalConflict = environmentalConflictMapper
					.getEnvironmentalConflictById(environmentalConflictCompanyCsvFile.getId_conflicto());

			if (null == environmentalConflict) {
				summary.append("Línea " + line + ": El conflicto ambiental con id_conflicto: "
						+ environmentalConflictCompanyCsvFile.getId_conflicto() + " no existe, ");
				continue;
			}

			if (StringUtils.isBlank(environmentalConflictCompanyCsvFile.getId_empresa())) {
				summary.append("Línea " + line + ": id_empresa no puede ser vacio ni nulo, ");
				continue;
			}

			Company company = companyMapper.getCompanyById(environmentalConflictCompanyCsvFile.getId_empresa());

			if (null == company) {
				summary.append("Línea " + line + ": La empresa con id_empresa: "
						+ environmentalConflictCompanyCsvFile.getId_empresa() + " no existe, ");
				continue;
			}

			EnvironmentalConflictCompany environmentalConflictCompany = transformEnvironmentalConflictCompany(
					environmentalConflictCompanyCsvFile);

			try {
				environmentalConflictCompanyMapper
						.addOrUpdateEnvironmentalConflictCompany(environmentalConflictCompany);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private EnvironmentalConflictCompany transformEnvironmentalConflictCompany(
			final EnvironmentalConflictCompanyCsvFile environmentalConflictCompanyCsvFile) {

		return EnvironmentalConflictCompany.builder()
				.environmentalConflictId(environmentalConflictCompanyCsvFile.getId_conflicto())
				.companyId(environmentalConflictCompanyCsvFile.getId_empresa())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
