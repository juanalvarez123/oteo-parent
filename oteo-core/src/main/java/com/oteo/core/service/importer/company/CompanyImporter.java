package com.oteo.core.service.importer.company;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Company;
import com.oteo.core.mybatis.mapper.CompanyMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.CompanyCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class CompanyImporter implements Importer<CompanyCsvFile> {

	private final CompanyMapper companyMapper;

	public CompanyImporter(CompanyMapper companyMapper) {

		this.companyMapper = companyMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, CompanyCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, CompanyCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			CompanyCsvFile companyCsvFile = entry.getValue();

			if (StringUtils.isBlank(companyCsvFile.getId_empresa())) {
				summary.append("Línea " + line + ": id_empresa no puede ser vacio ni nulo, ");
				continue;
			}

			Company company = transformCompany(companyCsvFile);

			try {
				companyMapper.addOrUpdateCompany(company);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private Company transformCompany(final CompanyCsvFile companyCsvFile) {

		return Company.builder()
				.companyId(companyCsvFile.getId_empresa())
				.name(companyCsvFile.getNombre_empresa())
				.type(companyCsvFile.getTipo_empresa())
				.country(companyCsvFile.getPais_empresa())
				.participation(companyCsvFile.getParticipacion_empresa())
				.description(companyCsvFile.getDescripcion())
				.link(companyCsvFile.getLink())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
