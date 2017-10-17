package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HumanRightViolationCsvFile {

	private String id_vulneracion;

	private String id_defensorx;

	private String agresion;

	private String actor_victimizante;

	private String fecha;

	private String observaciones;

	private String link;

}
