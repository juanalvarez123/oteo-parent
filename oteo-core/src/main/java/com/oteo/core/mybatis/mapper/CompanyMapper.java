package com.oteo.core.mybatis.mapper;

import com.oteo.core.mybatis.domain.Company;

public interface CompanyMapper {

	Company getCompanyById(String companyId);

	void addOrUpdateCompany(Company company);

}
