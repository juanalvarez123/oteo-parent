package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegislationCsvFile {

	private String id_legis;

	private String pais;

	private String fecha;

	private String organismo;

	private String tipo_legislacion;

	private String nombre_legislacion;

	private String observaciones;

	private String link;

	private String componente;

}
