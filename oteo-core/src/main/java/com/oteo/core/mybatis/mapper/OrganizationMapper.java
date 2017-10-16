package com.oteo.core.mybatis.mapper;

import com.oteo.core.mybatis.domain.Organization;

public interface OrganizationMapper {

	Organization getOrganizationById(String organizationId);

	void addOrUpdateOrganization(Organization organization);

}