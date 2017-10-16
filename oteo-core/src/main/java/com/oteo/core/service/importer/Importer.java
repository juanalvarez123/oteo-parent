package com.oteo.core.service.importer;

import java.util.Map;

public interface Importer<T> {

	ImportResponse importRecords(Map<Integer, T> records);

}
