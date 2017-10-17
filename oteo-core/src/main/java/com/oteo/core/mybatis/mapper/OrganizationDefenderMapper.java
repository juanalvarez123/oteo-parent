package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.OrganizationDefender;

public interface OrganizationDefenderMapper {

	List<OrganizationDefender> getDefendersByOrganizationId(String organizationId);

	void addOrUpdateOrganizationDefender(OrganizationDefender organizationDefender);

}
