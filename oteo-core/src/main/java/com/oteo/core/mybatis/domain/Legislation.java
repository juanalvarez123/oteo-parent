package com.oteo.core.mybatis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Legislation {

	private String legislationId;

	private String country;

	private LocalDate datetime;

	private String organism;

	private String type;

	private String name;

	private String observation;

	private String link;

	private String component;

	private String createdBy;

	private ZonedDateTime createdDatetime;

	private String modifiedBy;

	private ZonedDateTime modifiedDatetime;

}
