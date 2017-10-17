package com.oteo.core.service.importer.environmental.conflict;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.EnvironmentalConflict;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.EnvironmentalConflictCsvFile;
import com.oteo.core.util.OteoUtil;

public class EnvironmentalConflictImporter implements Importer<EnvironmentalConflictCsvFile> {

	private final EnvironmentalConflictMapper environmentalConflictMapper;

	public EnvironmentalConflictImporter(EnvironmentalConflictMapper environmentalConflictMapper) {

		this.environmentalConflictMapper = environmentalConflictMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, EnvironmentalConflictCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, EnvironmentalConflictCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			EnvironmentalConflictCsvFile environmentalConflictCsvFile = entry.getValue();

			if (StringUtils.isBlank(environmentalConflictCsvFile.getId_conflic())) {
				summary.append("Línea " + line + ": id_conflic no puede ser vacio ni nulo, ");
				continue;
			}

			EnvironmentalConflict environmentalConflict = transformEnvironmentalConflict(
					environmentalConflictCsvFile);

			try {
				environmentalConflictMapper.addOrUpdateEnvironmentalConflict(environmentalConflict);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private EnvironmentalConflict transformEnvironmentalConflict(
			final EnvironmentalConflictCsvFile environmentalConflictCsvFile) {

		return EnvironmentalConflict.builder()
				.environmentalConflictId(environmentalConflictCsvFile.getId_conflic())
				.country(environmentalConflictCsvFile.getGmi_cntry())
				.place(environmentalConflictCsvFile.getLugar())
				.name(environmentalConflictCsvFile.getNombre_confl())
				.latitude(OteoUtil.getDoubleFromString(environmentalConflictCsvFile.getLatitud()))
				.longitude(OteoUtil.getDoubleFromString(environmentalConflictCsvFile.getLongitud()))
				.datetime(OteoUtil.getLocalDateFromString(environmentalConflictCsvFile.getFecha()))
				.conflict(environmentalConflictCsvFile.getConflicto())
				.extractivism(environmentalConflictCsvFile.getExtractivismo())
				.resources(environmentalConflictCsvFile.getRecursos())
				.source(environmentalConflictCsvFile.getFuente())
				.link(environmentalConflictCsvFile.getLink())
				.build();
	}

}
