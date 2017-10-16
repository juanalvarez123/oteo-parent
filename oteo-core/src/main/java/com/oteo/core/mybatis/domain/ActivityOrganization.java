package com.oteo.core.mybatis.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityOrganization {

	private String activityOrganizationId;

	private String organizationId;

	private String strengthType;

	private String strengthDescription;

	private String results;

	private String resultName;

	private LocalDate datetime;

	private String link;

}
