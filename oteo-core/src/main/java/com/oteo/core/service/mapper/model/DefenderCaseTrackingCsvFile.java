package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefenderCaseTrackingCsvFile {

	private String id_seguimiento;

	private String id_caso;

	private String id_defensorx;

	private String nombre_caso;

	private String avance_caso;

	private String fecha;

	private String observacion;

	private String link;

}
