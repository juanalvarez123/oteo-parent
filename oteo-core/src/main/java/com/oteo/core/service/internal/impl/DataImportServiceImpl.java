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
import com.oteo.core.mybatis.mapper.OrganizationMapper;
import com.oteo.core.service.file.FileService;
import com.oteo.core.service.importer.ImportResponse;
import com.oteo.core.service.importer.Importer;
import com.oteo.core.service.importer.company.ActivityOrganizationImporter;
import com.oteo.core.service.importer.company.CompanyImporter;
import com.oteo.core.service.importer.company.OrganizationImporter;
import com.oteo.core.service.internal.DataImportService;
import com.oteo.core.service.mapper.MapperService;
import com.oteo.core.service.mapper.MapperServiceImpl;
import com.oteo.core.service.mapper.model.ActivityOrganizationCsvFile;
import com.oteo.core.service.mapper.model.CompanyCsvFile;
import com.oteo.core.service.mapper.model.OrganizationCsvFile;
import com.oteo.core.service.mapper.table.ActivityOrganizationTableMapper;
import com.oteo.core.service.mapper.table.CompanyTableMapper;
import com.oteo.core.service.mapper.table.OrganizationTableMapper;
import com.oteo.core.service.mapper.table.TableMapper;
import com.oteo.core.util.CsvFile;

@Service
class DataImportServiceImpl implements DataImportService {

	private final FileService fileService;

	private final CompanyMapper companyMapper;

	private final OrganizationMapper organizationMapper;

	private final ActivityOrganizationMapper activityOrganizationMapper;

	@SuppressWarnings("rawtypes")
	private MapperService mapperService;

	@SuppressWarnings("rawtypes")
	private Importer importer;

	@SuppressWarnings("rawtypes")
	private TableMapper tableMapper;

	@Autowired
	public DataImportServiceImpl(FileService fileService, CompanyMapper companyMapper,
			OrganizationMapper organizationMapper, ActivityOrganizationMapper activityOrganizationMapper) {

		this.fileService = fileService;
		this.companyMapper = companyMapper;
		this.organizationMapper = organizationMapper;
		this.activityOrganizationMapper = activityOrganizationMapper;
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
