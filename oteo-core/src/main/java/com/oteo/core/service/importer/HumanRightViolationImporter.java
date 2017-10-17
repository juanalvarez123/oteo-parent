package com.oteo.core.service.importer;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Defender;
import com.oteo.core.mybatis.domain.HumanRightViolation;
import com.oteo.core.mybatis.mapper.DefenderMapper;
import com.oteo.core.mybatis.mapper.HumanRightViolationMapper;
import com.oteo.core.service.mapper.model.HumanRightViolationCsvFile;
import com.oteo.core.util.OteoUtil;

public class HumanRightViolationImporter implements Importer<HumanRightViolationCsvFile> {

	private final DefenderMapper defenderMapper;

	private final HumanRightViolationMapper humanRightViolationMapper;

	public HumanRightViolationImporter(DefenderMapper defenderMapper,
			HumanRightViolationMapper humanRightViolationMapper) {

		this.defenderMapper = defenderMapper;
		this.humanRightViolationMapper = humanRightViolationMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, HumanRightViolationCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, HumanRightViolationCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			HumanRightViolationCsvFile humanRightViolationCsvFile = entry.getValue();

			if (StringUtils.isBlank(humanRightViolationCsvFile.getId_vulneracion())) {
				summary.append("Línea " + line + ": id_vulneracion no puede ser vacio ni nulo, ");
				continue;
			}

			if (StringUtils.isBlank(humanRightViolationCsvFile.getId_defensorx())) {
				summary.append("Línea " + line + ": id_defensorx no puede ser vacio ni nulo, ");
				continue;
			}

			Defender defender = defenderMapper.getDefenderById(humanRightViolationCsvFile.getId_defensorx());

			if (null == defender) {
				summary.append("Línea " + line + ": El defensor con id_defensorx: "
						+ humanRightViolationCsvFile.getId_defensorx() + " no existe, ");
				continue;
			}

			HumanRightViolation humanRightViolation = transformHumanRightViolation(humanRightViolationCsvFile);

			try {
				humanRightViolationMapper.addOrUpdateHumanRightViolation(humanRightViolation);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private HumanRightViolation transformHumanRightViolation(
			final HumanRightViolationCsvFile humanRightViolationCsvFile) {

		return HumanRightViolation.builder()
				.humanRightViolationId(humanRightViolationCsvFile.getId_vulneracion())
				.defenderId(humanRightViolationCsvFile.getId_defensorx())
				.attack(humanRightViolationCsvFile.getAgresion())
				.responsible(humanRightViolationCsvFile.getActor_victimizante())
				.datetime(OteoUtil.getLocalDateFromString(humanRightViolationCsvFile.getFecha()))
				.observation(humanRightViolationCsvFile.getObservaciones())
				.link(humanRightViolationCsvFile.getLink())
				.build();
	}

}
