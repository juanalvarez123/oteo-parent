package com.oteo.core.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

	private String organizationId;

	private String name;

	private String country;

	private String place;

	private String organizationClass;

	private String type;

	private String goal;

	private String link;

	private Boolean active;

}
