package com.oteo.core.mybatis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentalConflict {

	private String environmentalConflictId;

	private String country;

	private String place;

	private String name;

	private Double latitude;

	private Double longitude;

	private LocalDate datetime;

	private String conflict;

	private String extractivism;

	private String resources;

	private String source;

	private String link;

	private String createdBy;

	private ZonedDateTime createdDatetime;

	private String modifiedBy;

	private ZonedDateTime modifiedDatetime;

}
