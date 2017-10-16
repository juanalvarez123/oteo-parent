package com.oteo.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class OteoUtil {

	public static Boolean getBooleanFromString(final String source) {

		if (StringUtils.isBlank(source)) {
			return null;
		}

		if (source.equalsIgnoreCase("si") || source.equalsIgnoreCase("sí")) {
			return true;
		}

		return false;
	}

	public static LocalDate getLocalDateFromString(final String source) {

		if (StringUtils.isBlank(source)) {
			return null;
		}

		return LocalDate.parse(source, DateTimeFormatter.ofPattern("M/d/yyyy"));
	}

}
