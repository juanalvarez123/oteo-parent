package com.oteo.core.service.internal;

import org.springframework.web.multipart.MultipartFile;

import com.oteo.core.exception.ServiceException;
import com.oteo.core.service.importer.ImportResponse;
import com.oteo.core.util.CsvFile;

public interface DataImportService {

	ImportResponse processCsvFile(MultipartFile file, CsvFile csvFile) throws ServiceException;

}
