package com.oteo.core.service.file;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oteo.core.exception.ServiceException;

public interface FileService {

	List<String> getLines(MultipartFile file) throws IOException, ServiceException;

}
