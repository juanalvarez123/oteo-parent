package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnvironmentalConflictCsvFile {

	private String id_conflic;

	private String gmi_cntry;

	private String lugar;

	private String nombre_confl;

	private String latitud;

	private String longitud;

	private String fecha;

	private String conflicto;

	private String extractivismo;

	private String recursos;

	private String fuente;

	private String link;

}
