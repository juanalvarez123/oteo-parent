package com.oteo.core.service.importer.company;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.mapper.CompanyMapper;
import com.oteo.core.service.importer.ImportResponse;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.CompanyCsvFile;

public class CompanyImporter implements Importer<CompanyCsvFile> {

	private final CompanyMapper companyMapper;

	public CompanyImporter(CompanyMapper companyMapper) {

		this.companyMapper = companyMapper;
	}

	@Override
	public ImportResponse importRecords(final Map<Integer, CompanyCsvFile> records) {

		long recordsAddedOrUpdated = 0;
		long recordsWithErrors = 0;
		StringBuilder summary = new StringBuilder();

		for (Map.Entry<Integer, CompanyCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			CompanyCsvFile companyCsvFile = entry.getValue();

			if (StringUtils.isBlank(companyCsvFile.getId_empresa())) {
				summary.append(line + ": id_empresa no puede ser vacio ni nulo, ");
				recordsWithErrors++;
				continue;
			}

			try {
				companyMapper.addOrUpdateCompany(companyCsvFile);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
				recordsWithErrors++;
			}
		}

		return ImportResponse.builder()
				.recordsAddedOrUpdated(recordsAddedOrUpdated)
				.recordsWithErrors(recordsWithErrors)
				.summary(StringUtils.isNotBlank(summary) ? summary.toString() : null)
				.build();
	}

}
