package com.oteo.core.mybatis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HumanRightViolation {

	private String humanRightViolationId;

	private String defenderId;

	private String attack;

	private String responsible;

	private LocalDate datetime;

	private String observation;

	private String link;

	private String createdBy;

	private ZonedDateTime createdDatetime;

	private String modifiedBy;

	private ZonedDateTime modifiedDatetime;

}
