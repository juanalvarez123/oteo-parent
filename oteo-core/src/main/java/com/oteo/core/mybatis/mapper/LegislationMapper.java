package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.Legislation;

public interface LegislationMapper {

	List<Legislation> getLegislationsByCountry(String country);

	void addOrUpdateLegislation(Legislation legislation);

}
