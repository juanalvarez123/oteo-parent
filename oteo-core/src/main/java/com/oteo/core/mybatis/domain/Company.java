package com.oteo.core.mybatis.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {

	private String companyId;

	private String name;

	private String type;

	private String country;

	private String participation;

	private String description;

	private String link;

}