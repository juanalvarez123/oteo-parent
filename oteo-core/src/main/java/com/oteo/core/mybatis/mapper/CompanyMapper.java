package com.oteo.core.mybatis.mapper;

import org.springframework.transaction.annotation.Transactional;

import com.oteo.core.mybatis.domain.Company;
import com.oteo.core.service.mapper.model.CompanyCsvFile;

public interface CompanyMapper {

	Company getCompanyById(String companyId);

	// TODO JSAE test
	@Transactional
	void addOrUpdateCompany(CompanyCsvFile companyCsvFile);

}
