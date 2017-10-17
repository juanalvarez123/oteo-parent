package com.oteo.core.mybatis.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnvironmentalConflictOrganization {

	private String environmentalConflictId;

	private String organizationId;

}
