package com.oteo.core.service.internal.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oteo.core.exception.ServiceException;
import com.oteo.core.mybatis.mapper.ActivityOrganizationMapper;
import com.oteo.core.mybatis.mapper.CompanyMapper;
import com.oteo.core.mybatis.mapper.DefenderCaseTrackingMapper;
import com.oteo.core.mybatis.mapper.DefenderMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictCompanyMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictMapper;
import com.oteo.core.mybatis.mapper.EnvironmentalConflictOrganizationMapper;
import com.oteo.core.mybatis.mapper.HumanRightViolationMapper;
import com.oteo.core.mybatis.mapper.LegislationMapper;
import com.oteo.core.mybatis.mapper.OrganizationDefenderMapper;
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.file.FileService;
import com.oteo.core.service.importer.HumanRightViolationImporter;
import com.oteo.core.service.importer.ImportResponse;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.importer.LegislationImporter;
import com.oteo.core.service.importer.company.CompanyImporter;
import com.oteo.core.service.importer.defender.DefenderCaseTrackingImporter;
import com.oteo.core.service.importer.defender.DefenderImporter;
import com.oteo.core.service.importer.defender.OrganizationDefenderImporter;
import com.oteo.core.service.importer.environmental.conflict.EnvironmentalConflictCompanyImporter;
import com.oteo.core.service.importer.environmental.conflict.EnvironmentalConflictImporter;
import com.oteo.core.service.importer.environmental.conflict.EnvironmentalConflictOrganizationImporter;
import com.oteo.core.service.importer.organization.ActivityOrganizationImporter;
import com.oteo.core.service.importer.organization.OrganizationImporter;
import com.oteo.core.service.internal.DataImportService;
import com.oteo.core.service.mapper.MapperService;
import com.oteo.core.service.mapper.MapperServiceImpl;
import com.oteo.core.service.mapper.model.ActivityOrganizationCsvFile;
import com.oteo.core.service.mapper.model.CompanyCsvFile;
import com.oteo.core.service.mapper.model.DefenderCaseTrackingCsvFile;
import com.oteo.core.service.mapper.model.DefenderCsvFile;
import com.oteo.core.service.mapper.model.EnvironmentalConflictCompanyCsvFile;
import com.oteo.core.service.mapper.model.EnvironmentalConflictCsvFile;
import com.oteo.core.service.mapper.model.EnvironmentalConflictOrganizationCsvFile;
import com.oteo.core.service.mapper.model.HumanRightViolationCsvFile;
import com.oteo.core.service.mapper.model.LegislationCsvFile;
import com.oteo.core.service.mapper.model.OrganizationCsvFile;
import com.oteo.core.service.mapper.model.OrganizationDefenderCsvFile;
import com.oteo.core.service.mapper.table.HumanRightViolationTableMapper;
import com.oteo.core.service.mapper.table.LegislationTableMapper;
import com.oteo.core.service.mapper.table.TableMapper;
import com.oteo.core.service.mapper.table.company.CompanyTableMapper;
import com.oteo.core.service.mapper.table.defender.DefenderCaseTrackingTableMapper;
import com.oteo.core.service.mapper.table.defender.DefenderTableMapper;
import com.oteo.core.service.mapper.table.defender.OrganizationDefenderTableMapper;
import com.oteo.core.service.mapper.table.environmental.conflict.EnvironmentalConflictCompanyTableMapper;
import com.oteo.core.service.mapper.table.environmental.conflict.EnvironmentalConflictOrganizationTableMapper;
import com.oteo.core.service.mapper.table.environmental.conflict.EnvironmentalConflictTableMapper;
import com.oteo.core.service.mapper.table.organization.ActivityOrganizationTableMapper;
import com.oteo.core.service.mapper.table.organization.OrganizationTableMapper;
import com.oteo.core.util.CsvFile;

@Service
class DataImportServiceImpl implements DataImportService {

	private final FileService fileService;

	private final ActivityOrganizationMapper activityOrganizationMapper;

	private final CompanyMapper companyMapper;

	private final DefenderCaseTrackingMapper defenderCaseTrackingMapper;

	private final DefenderMapper defenderMapper;

	private final EnvironmentalConflictCompanyMapper environmentalConflictCompanyMapper;

	private final EnvironmentalConflictMapper environmentalConflictMapper;

	private final EnvironmentalConflictOrganizationMapper environmentalConflictOrganizationMapper;

	private final HumanRightViolationMapper humanRightViolationMapper;

	private final LegislationMapper legislationMapper;

	private final OrganizationDefenderMapper organizationDefenderMapper;

	private final OrganizationMapper organizationMapper;

	@SuppressWarnings("rawtypes")
	private MapperService mapperService;

	@SuppressWarnings("rawtypes")
	private Importer importer;

	@SuppressWarnings("rawtypes")
	private TableMapper tableMapper;

	@Autowired
	public DataImportServiceImpl(FileService fileService,
			ActivityOrganizationMapper activityOrganizationMapper,
			CompanyMapper companyMapper,
			DefenderCaseTrackingMapper defenderCaseTrackingMapper,
			DefenderMapper defenderMapper,
			EnvironmentalConflictCompanyMapper environmentalConflictCompanyMapper,
			EnvironmentalConflictMapper environmentalConflictMapper,
			EnvironmentalConflictOrganizationMapper environmentalConflictOrganizationMapper,
			HumanRightViolationMapper humanRightViolationMapper,
			LegislationMapper legislationMapper,
			OrganizationDefenderMapper organizationDefenderMapper,
			OrganizationMapper organizationMapper) {

		this.fileService = fileService;
		this.activityOrganizationMapper = activityOrganizationMapper;
		this.companyMapper = companyMapper;
		this.defenderCaseTrackingMapper = defenderCaseTrackingMapper;
		this.defenderMapper = defenderMapper;
		this.environmentalConflictCompanyMapper = environmentalConflictCompanyMapper;
		this.environmentalConflictMapper = environmentalConflictMapper;
		this.environmentalConflictOrganizationMapper = environmentalConflictOrganizationMapper;
		this.humanRightViolationMapper = humanRightViolationMapper;
		this.legislationMapper = legislationMapper;
		this.organizationDefenderMapper = organizationDefenderMapper;
		this.organizationMapper = organizationMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImportResponse processCsvFile(final MultipartFile file, final CsvFile csvFile)
			throws ServiceException {

		switch (csvFile) {

			case COMPANY:
				mapperService = new MapperServiceImpl<CompanyCsvFile>();
				importer = new CompanyImporter(companyMapper);
				tableMapper = new CompanyTableMapper();
				break;

			case ORGANIZATION:
				mapperService = new MapperServiceImpl<OrganizationCsvFile>();
				importer = new OrganizationImporter(organizationMapper);
				tableMapper = new OrganizationTableMapper();
				break;

			case ACTIVITY_ORGANIZATION:
				mapperService = new MapperServiceImpl<ActivityOrganizationCsvFile>();
				importer = new ActivityOrganizationImporter(activityOrganizationMapper, organizationMapper);
				tableMapper = new ActivityOrganizationTableMapper();
				break;

			case ENVIRONMENTAL_CONFLICT:
				mapperService = new MapperServiceImpl<EnvironmentalConflictCsvFile>();
				importer = new EnvironmentalConflictImporter(environmentalConflictMapper);
				tableMapper = new EnvironmentalConflictTableMapper();
				break;

			case ENVIRONMENTAL_CONFLICT_COMPANY:
				mapperService = new MapperServiceImpl<EnvironmentalConflictCompanyCsvFile>();
				importer = new EnvironmentalConflictCompanyImporter(environmentalConflictMapper,
						companyMapper, environmentalConflictCompanyMapper);
				tableMapper = new EnvironmentalConflictCompanyTableMapper();
				break;

			case ENVIRONMENTAL_CONFLICT_ORGANIZATION:
				mapperService = new MapperServiceImpl<EnvironmentalConflictOrganizationCsvFile>();
				importer = new EnvironmentalConflictOrganizationImporter(environmentalConflictMapper,
						organizationMapper, environmentalConflictOrganizationMapper);
				tableMapper = new EnvironmentalConflictOrganizationTableMapper();
				break;

			case DEFENDER:
				mapperService = new MapperServiceImpl<DefenderCsvFile>();
				importer = new DefenderImporter(defenderMapper);
				tableMapper = new DefenderTableMapper();
				break;

			case ORGANIZATION_DEFENDER:
				mapperService = new MapperServiceImpl<OrganizationDefenderCsvFile>();
				importer = new OrganizationDefenderImporter(defenderMapper, organizationMapper,
						organizationDefenderMapper);
				tableMapper = new OrganizationDefenderTableMapper();
				break;

			case DEFENDER_CASE_TRACKING:
				mapperService = new MapperServiceImpl<DefenderCaseTrackingCsvFile>();
				importer = new DefenderCaseTrackingImporter(defenderMapper, defenderCaseTrackingMapper);
				tableMapper = new DefenderCaseTrackingTableMapper();
				break;

			case HUMAN_RIGHT_VIOLATION:
				mapperService = new MapperServiceImpl<HumanRightViolationCsvFile>();
				importer = new HumanRightViolationImporter(defenderMapper, humanRightViolationMapper);
				tableMapper = new HumanRightViolationTableMapper();
				break;

			case LEGISLATION:
				mapperService = new MapperServiceImpl<LegislationCsvFile>();
				importer = new LegislationImporter(legislationMapper);
				tableMapper = new LegislationTableMapper();
				break;

			default:
				throw new ServiceException("Invalid type to export data");
		}

		try {
			List<String> linesFromFile = fileService.getLines(file);
			Map<Integer, Object> mappedRecords = mapperService.getModel(linesFromFile, tableMapper);
			return importer.importRecords(mappedRecords);
		} catch (IOException ex) {
			throw new ServiceException("Error reading attached file", ex);
		}
	}

}
