package com.oteo.core.mybatis.mapper;

import com.oteo.core.mybatis.domain.Defender;

public interface DefenderMapper {

	Defender getDefenderById(String defenderId);

	void addOrUpdateDefender(Defender defender);

}
