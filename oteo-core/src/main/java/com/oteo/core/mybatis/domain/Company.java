package com.oteo.core.mybatis.domain;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	private String companyId;

	private String name;

	private String type;

	private String country;

	private String participation;

	private String description;

	private String link;

	private String createdBy;

	private ZonedDateTime createdDatetime;

	private String modifiedBy;

	private ZonedDateTime modifiedDatetime;

}
