package com.oteo.core.service.importer.defender;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oteo.core.mybatis.domain.Defender;
import com.oteo.core.mybatis.domain.DefenderCaseTracking;
import com.oteo.core.mybatis.mapper.DefenderCaseTrackingMapper;
import com.oteo.core.mybatis.mapper.DefenderMapper;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.mapper.model.DefenderCaseTrackingCsvFile;
import com.oteo.core.util.OteoConstant;
import com.oteo.core.util.OteoUtil;

public class DefenderCaseTrackingImporter implements Importer<DefenderCaseTrackingCsvFile> {

	private final DefenderMapper defenderMapper;

	private final DefenderCaseTrackingMapper defenderCaseTrackingMapper;

	public DefenderCaseTrackingImporter(DefenderMapper defenderMapper,
			DefenderCaseTrackingMapper defenderCaseTrackingMapper) {

		this.defenderMapper = defenderMapper;
		this.defenderCaseTrackingMapper = defenderCaseTrackingMapper;
	}

	@Override
	public long iterateMap(final Map<Integer, DefenderCaseTrackingCsvFile> records, StringBuilder summary) {

		long recordsAddedOrUpdated = 0L;

		for (Map.Entry<Integer, DefenderCaseTrackingCsvFile> entry : records.entrySet()) {

			long line = entry.getKey();
			DefenderCaseTrackingCsvFile defenderCaseTrackingCsvFile = entry.getValue();

			if (StringUtils.isBlank(defenderCaseTrackingCsvFile.getId_caso())) {
				summary.append("Línea " + line + ": id_caso no puede ser vacio ni nulo, ");
				continue;
			}

			if (StringUtils.isBlank(defenderCaseTrackingCsvFile.getId_defensorx())) {
				summary.append("Línea " + line + ": id_defensorx no puede ser vacio ni nulo, ");
				continue;
			}

			Defender defender = defenderMapper.getDefenderById(defenderCaseTrackingCsvFile.getId_defensorx());

			if (null == defender) {
				summary.append("Línea " + line + ": El defensor con id_defensorx: "
						+ defenderCaseTrackingCsvFile.getId_defensorx() + " no existe, ");
				continue;
			}

			DefenderCaseTracking defenderCaseTracking = transformDefenderCaseTracking(
					defenderCaseTrackingCsvFile);

			try {
				defenderCaseTrackingMapper.addOrUpdateDefenderCaseTracking(defenderCaseTracking);
				recordsAddedOrUpdated++;
			} catch (Exception ex) {
				summary.append(line + ": " + ex.getMessage() + "\n");
			}
		}

		return recordsAddedOrUpdated;
	}

	private DefenderCaseTracking transformDefenderCaseTracking(
			final DefenderCaseTrackingCsvFile defenderCaseTrackingCsvFile) {

		return DefenderCaseTracking.builder()
				.defenderCaseTrackingId(defenderCaseTrackingCsvFile.getId_caso())
				.defenderId(defenderCaseTrackingCsvFile.getId_defensorx())
				.name(defenderCaseTrackingCsvFile.getNombre_caso())
				.progress(defenderCaseTrackingCsvFile.getAvance_caso())
				.datetime(OteoUtil.getLocalDateFromString(defenderCaseTrackingCsvFile.getFecha()))
				.observation(defenderCaseTrackingCsvFile.getObservacion())
				.link(defenderCaseTrackingCsvFile.getLink())
				.createdBy(OteoConstant.OTEO_ADMIN_USER)
				.createdDatetime(OteoUtil.now())
				.modifiedBy(OteoConstant.OTEO_ADMIN_USER)
				.modifiedDatetime(OteoUtil.now())
				.build();
	}

}
