package com.oteo.core.mybatis.mapper;

import com.oteo.core.mybatis.domain.DefenderCaseTracking;

public interface DefenderCaseTrackingMapper {

	DefenderCaseTracking getCaseTrackingByDefenderId(String defenderId);

	void addOrUpdateDefenderCaseTracking(DefenderCaseTracking defenderCaseTracking);

}
