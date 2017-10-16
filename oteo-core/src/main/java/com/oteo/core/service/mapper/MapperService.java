package com.oteo.core.service.mapper;

import java.util.List;
import java.util.Map;

import com.oteo.core.service.mapper.table.TableMapper;

public interface MapperService<T> {

	Map<Integer, T> getModel(List<String> lines, TableMapper<T> tableMapper);

}
