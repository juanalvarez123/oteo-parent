package com.oteo.core.mybatis.domain;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnvironmentalConflictOrganization {

	private String environmentalConflictId;

	private String organizationId;

	private String createdBy;

	private ZonedDateTime createdDatetime;

	private String modifiedBy;

	private ZonedDateTime modifiedDatetime;

}
