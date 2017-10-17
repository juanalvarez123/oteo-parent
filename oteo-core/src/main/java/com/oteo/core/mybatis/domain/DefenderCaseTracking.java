package com.oteo.core.mybatis.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefenderCaseTracking {

	private String defenderCaseTrackingId;

	private String defenderId;

	private String name;

	private String progress;

	private LocalDate datetime;

	private String observation;

	private String link;

}
