package com.oteo.core.service.internal;

import java.sql.SQLException;

public interface DatabaseService {

	boolean isDatabaseActive() throws SQLException;

}
