package com.oteo.core.service.file.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oteo.core.exception.ServiceException;
import com.oteo.core.service.file.FileService;

@Service
class FileServiceImpl implements FileService {

	@Override
	public List<String> getLines(final MultipartFile file) throws IOException, ServiceException {

		byte[] bytes = file.getBytes();
		String completeData = new String(bytes, StandardCharsets.ISO_8859_1);

		if (StringUtils.isBlank(completeData)) {
			throw new ServiceException("Empty file");
		}

		List<String> result = new LinkedList<>();
		result.addAll(Arrays.asList(completeData.split("\n")));

		return result;
	}

}
