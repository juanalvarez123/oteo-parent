package com.oteo.core.service.importer;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Legislation;
import com.oteo.core.mybatis.mapper.LegislationMapper;
import com.oteo.core.service.mapper.model.LegislationCsvFile;
import com.oteo.core.util.OteoUtil;

public class LegislationImporter implements Importer<LegislationCsvFile> {

	private final LegislationMapper legislationMapper;

	public LegislationImporter(LegislationMapper legislationMapper) {

		this.legislationMapper = legislationMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, LegislationCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, LegislationCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			LegislationCsvFile legislationCsvFile = entry.getValue();

			if (StringUtils.isBlank(legislationCsvFile.getId_legis())) {
				summary.append("Línea " + line + ": id_legis no puede ser vacio ni nulo, ");
				continue;
			}

			Legislation legislation = transformLegislation(legislationCsvFile);

			try {
				legislationMapper.addOrUpdateLegislation(legislation);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private Legislation transformLegislation(final LegislationCsvFile legislationCsvFile) {

		return Legislation.builder()
				.legislationId(legislationCsvFile.getId_legis())
				.country(legislationCsvFile.getPais())
				.datetime(OteoUtil.getLocalDateFromString(legislationCsvFile.getFecha()))
				.organism(legislationCsvFile.getOrganismo())
				.type(legislationCsvFile.getTipo_legislacion())
				.name(legislationCsvFile.getNombre_legislacion())
				.observation(legislationCsvFile.getObservaciones())
				.link(legislationCsvFile.getLink())
				.component(legislationCsvFile.getComponente())
				.build();
	}

}
