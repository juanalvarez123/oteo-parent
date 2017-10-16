package com.oteo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oteo.core.exception.ServiceException;
import com.oteo.core.service.importer.ImportResponse;
import com.oteo.core.service.internal.DataImportService;
import com.oteo.core.util.CsvFile;

@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
@RestController
public class DataImportController {

	private final DataImportService dataImportService;

	@Autowired
	public DataImportController(DataImportService dataImportService) {

		this.dataImportService = dataImportService;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/file/{csvFile}",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ImportResponse importDataFromCsvFile(
			@RequestParam(name = "file", required = true) final MultipartFile file,
			@PathVariable final CsvFile csvFile) throws ServiceException {

		return dataImportService.processCsvFile(file, csvFile);
	}

}
