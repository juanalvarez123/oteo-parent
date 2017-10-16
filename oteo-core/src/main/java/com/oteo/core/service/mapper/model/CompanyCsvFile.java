package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyCsvFile {

	private String id_empresa;

	private String nombre_empresa;

	private String tipo_empresa;

	private String pais_empresa;

	private String participacion_empresa;

	private String descripcion;

	private String link;

}
