package com.oteo.core.service.importer.defender;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Defender;
import com.oteo.core.mybatis.mapper.DefenderMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.DefenderCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class DefenderImporter implements Importer<DefenderCsvFile> {

	private final DefenderMapper defenderMapper;

	public DefenderImporter(DefenderMapper defenderMapper) {

		this.defenderMapper = defenderMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, DefenderCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, DefenderCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			DefenderCsvFile defenderCsvFile = entry.getValue();

			if (StringUtils.isBlank(defenderCsvFile.getId_defensorx())) {
				summary.append("Línea " + line + ": id_defensorx no puede ser vacio ni nulo, ");
				continue;
			}

			Defender defender = transformDefender(defenderCsvFile);

			try {
				defenderMapper.addOrUpdateDefender(defender);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private Defender transformDefender(final DefenderCsvFile defenderCsvFile) {

		return Defender.builder()
				.defenderId(defenderCsvFile.getId_defensorx())
				.name(defenderCsvFile.getNombre())
				.age(OteoUtil.getIntegerFromString(defenderCsvFile.getEdad()))
				.sex(defenderCsvFile.getSexo())
				.gender(defenderCsvFile.getGenero())
				.populationGroup(defenderCsvFile.getGrupo_poblacional())
				.profession(defenderCsvFile.getProfesion())
				.position(defenderCsvFile.getCargo())
				.awards(defenderCsvFile.getPremios())
				.alive(OteoUtil.getBooleanFromString(defenderCsvFile.getVivx()))
				.deathDatetime(OteoUtil.getLocalDateFromString(defenderCsvFile.getFecha_muerte()))
				.observation(defenderCsvFile.getObservacion())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
