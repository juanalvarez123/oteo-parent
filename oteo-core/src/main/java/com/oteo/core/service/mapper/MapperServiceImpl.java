package com.oteo.core.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oteo.core.service.mapper.table.TableMapper;

public class MapperServiceImpl<T> implements MapperService<T> {

	@Override
	public Map<Integer, T> getModel(final List<String> lines, final TableMapper<T> tableMapper) {

		Map<Integer, T> model = new HashMap<>();

		for(int i=1 ; i<lines.size() ; i++) {
			model.put(i, tableMapper.map(lines.get(i)));
		};

		return model;
	}

}
