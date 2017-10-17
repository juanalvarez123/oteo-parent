package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.EnvironmentalConflict;

public interface EnvironmentalConflictMapper {

	EnvironmentalConflict getEnvironmentalConflictById(String environmentalConflictId);

	List<EnvironmentalConflict> getEnvironmentalConflictsByCountry(String country);

	void addOrUpdateEnvironmentalConflict(EnvironmentalConflict environmentalConflict);

}
