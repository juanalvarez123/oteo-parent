package com.oteo.core.util;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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

		try {
			return LocalDate.parse(source, DateTimeFormatter.ofPattern("M/d/yyyy"));
		} catch (Exception ex) {
			return null;
		}
	}

	public static Integer getIntegerFromString(final String source) {

		if (StringUtils.isBlank(source)) {
			return null;
		}

		try {
			return Integer.parseInt(source);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Double getDoubleFromString(final String source) {

		if (StringUtils.isBlank(source)) {
			return null;
		}

		try {
			return Double.parseDouble(source.replace(",", "."));
		} catch (Exception ex) {
			return null;
		}
	}

	public static ZonedDateTime now() {

		return ZonedDateTime.now();
	}

}
