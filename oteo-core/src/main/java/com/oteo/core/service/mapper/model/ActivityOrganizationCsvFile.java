package com.oteo.core.service.mapper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityOrganizationCsvFile {

	private String id_actividad;

	private String id_organizacion;

	private String tipo_resistencia;

	private String descrip_resistencias;

	private String materiales_produc;

	private String nombre_material;

	private String fecha;

	private String link;

}
