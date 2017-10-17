package com.oteo.core.mybatis.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationDefender {

	private String defenderId;

	private String organizationId;

}
