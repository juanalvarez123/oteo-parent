package com.oteo.core.service.mapper.table;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public interface TableMapper<T> {

	T map(String line);

	default List<String> cleanRow(String line) {

		String[] dataAsArray = line.split(";");
		List<String> dataAsList = Arrays.asList(dataAsArray);

		for (int i = 0; i < dataAsList.size(); i++) {

			dataAsList.set(i, dataAsList.get(i).replace("\r", StringUtils.EMPTY));

			if (StringUtils.isBlank(dataAsList.get(i))) {
				dataAsList.set(i, null);
			}
		}

		return dataAsList;
	}

}
