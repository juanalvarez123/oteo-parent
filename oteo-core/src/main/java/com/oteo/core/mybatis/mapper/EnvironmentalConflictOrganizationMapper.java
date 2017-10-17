package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.EnvironmentalConflictOrganization;

public interface EnvironmentalConflictOrganizationMapper {

	List<EnvironmentalConflictOrganization> getEnvironmentalConflictsByOrganizationId(String organizationId);

	void addOrUpdateEnvironmentalConflictOrganization(
			EnvironmentalConflictOrganization environmentalConflictOrganization);

}
