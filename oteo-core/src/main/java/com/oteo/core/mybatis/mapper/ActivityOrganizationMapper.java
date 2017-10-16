package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.ActivityOrganization;

public interface ActivityOrganizationMapper {

	ActivityOrganization getActivityOrganizationById(String activityOrganizationId);

	List<ActivityOrganization> getActivityOrganizationByOrganizationId(String organizationId);

	void addOrUpdateActivityOrganization(ActivityOrganization activityOrganization);

}
