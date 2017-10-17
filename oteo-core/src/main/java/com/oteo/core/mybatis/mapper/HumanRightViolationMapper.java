package com.oteo.core.mybatis.mapper;

import java.util.List;

import com.oteo.core.mybatis.domain.HumanRightViolation;

public interface HumanRightViolationMapper {

	List<HumanRightViolation> getHumanRightViolationsByDefenderId(String defenderId);

	void addOrUpdateHumanRightViolation(HumanRightViolation humanRightViolation);

}
