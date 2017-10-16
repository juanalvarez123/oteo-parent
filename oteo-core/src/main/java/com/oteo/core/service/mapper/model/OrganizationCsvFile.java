package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationCsvFile {

	private String id_organizacion;

	private String nombre_organizacion;

	private String pais;

	private String lugar;

	private String clase_organizacion;

	private String tipo_organ;

	private String objetivo;

	private String link_organizacion;

	private String activa;

}
