package com.oteo.core.mybatis.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Defender {

	private String defenderId;

	private String name;

	private Integer age;

	private String sex;

	private String gender;

	private String populationGroup;

	private String profession;

	private String position;

	private String awards;

	private Boolean alive;

	private LocalDate deathDatetime;

	private String observation;

}
