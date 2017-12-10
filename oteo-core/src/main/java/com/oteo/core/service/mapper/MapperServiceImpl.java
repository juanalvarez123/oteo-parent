package com.oteo.core.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oteo.core.exception.MapperException;
import com.oteo.core.service.mapper.table.TableMapper;

public class MapperServiceImpl<T> implements MapperService<T> {

	@Override
	public Map<Integer, T> getModel(final List<String> lines, final TableMapper<T> tableMapper) {

		Map<Integer, T> model = new HashMap<>();

		for(int i=1 ; i<lines.size() ; i++) {
			try {
				model.put((i+1), tableMapper.map(lines.get(i)));
			} catch (ArrayIndexOutOfBoundsException ex) {
				throw new MapperException(lines.get(i).concat(" ... is wrong, please fix it"), ex);
			}
		};

		return model;
	}

}
