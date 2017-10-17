package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.EnvironmentalConflictCompany;

public interface EnvironmentalConflictCompanyMapper {

	List<EnvironmentalConflictCompany> getEnvironmentalConflictsByCompanyId(String CompanyId);

	void addOrUpdateEnvironmentalConflictCompany(
			EnvironmentalConflictCompany environmentalConflictCompany);

}
