package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefenderCsvFile {

	private String id_defensorx;

	private String nombre;

	private String edad;

	private String sexo;

	private String genero;

	private String grupo_poblacional;

	private String profesion;

	private String cargo;

	private String premios;

	private String vivx;

	private String fecha_muerte;

	private String observacion;

}
