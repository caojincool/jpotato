package com.lemsun.data.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 内部执行模板
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午3:21
 */
public class JdbcTemplate {

	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

	private TransactionManager transactionManager;

	private NativeJdbcExtractor nativeJdbcExtractor;
	private int fetchSize = 0;
	private int maxRows = 0;
	private int queryTimeout = 0;


	public JdbcTemplate(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


	/**
	 * 设置批处理的数量
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * 返回批处理的数据
	 */
	public int getFetchSize() {
		return this.fetchSize;
	}


	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	/**
	 * Return the maximum number of rows specified for this JdbcTemplate.
	 */
	public int getMaxRows() {
		return this.maxRows;
	}


	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	/**
	 * Return the query timeout for statements that this JdbcTemplate executes.
	 */
	public int getQueryTimeout() {
		return this.queryTimeout;
	}

	/**
	 * Set a NativeJdbcExtractor to extract native JDBC objects from wrapped handles.
	 * Useful if native Statement and/or ResultSet handles are expected for casting
	 * to database-specific implementation classes, but a connection pool that wraps
	 * JDBC objects is used (note: <i>any</i> pool will return wrapped Connections).
	 */
	public void setNativeJdbcExtractor(NativeJdbcExtractor extractor) {
		this.nativeJdbcExtractor = extractor;
	}

	/**
	 * Return the current NativeJdbcExtractor implementation.
	 */
	public NativeJdbcExtractor getNativeJdbcExtractor() {
		return this.nativeJdbcExtractor;
	}


	public <T> T query(final String db,
			PreparedStatementCreator psc, final PreparedStatementSetter pss, final ResultSetExtractor<T> rse)
			throws SQLException {

		Assert.notNull(rse, "ResultSetExtractor must not be null");

		return execute(db, psc, new PreparedStatementCallback<T>() {
			public T doInPreparedStatement(PreparedStatement ps) throws SQLException {
				ResultSet rs = null;
				try {
					if (pss != null) {
						pss.setValues(ps);
					}
					rs = ps.executeQuery();
					ResultSet rsToUse = rs;
					if (nativeJdbcExtractor != null) {
						rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
					}
					return rse.extractData(rsToUse);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
					if (pss instanceof ParameterDisposer) {
						((ParameterDisposer) pss).cleanupParameters();
					}
				}
			}
		});
	}


	public <T> T query(final String db, final String sql, final ResultSetExtractor<T> rse) throws SQLException {
		Assert.notNull(sql, "SQL 语句不能为空");
		Assert.notNull(rse, "结果集不能为空");

		Connection conn = transactionManager.getConnection(db);

		class QueryStatementCallback implements StatementCallback<T>, SqlProvider {
			public T doInStatement(Statement stmt) throws SQLException {
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(sql);
					ResultSet rsToUse = rs;
					if (nativeJdbcExtractor != null) {
						rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
					}
					return rse.extractData(rsToUse);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
				}
			}
			public String getSql() {
				return sql;
			}
		}

		return execute(db, new QueryStatementCallback());
	}


	public <T> T query(final String db, PreparedStatementCreator psc, ResultSetExtractor<T> rse) throws SQLException {
		return query(db, psc, null, rse);
	}

	public <T> T query(String db, String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse) throws SQLException {
		return query(db, new SimplePreparedStatementCreator(sql), pss, rse);
	}

	public <T> T query(String db, String sql, Object[] args, int[] argTypes, ResultSetExtractor<T> rse) throws SQLException {
		return query(db, sql, newArgTypePreparedStatementSetter(args, argTypes), rse);
	}

	public <T> T query(String db, String sql, Object[] args, ResultSetExtractor<T> rse) throws SQLException {
		return query(db, sql, newArgPreparedStatementSetter(args), rse);
	}

	public <T> T query(String db, String sql, ResultSetExtractor<T> rse, Object... args) throws SQLException {
		return query(db, sql, newArgPreparedStatementSetter(args), rse);
	}

	public void query(String db, PreparedStatementCreator psc, RowCallbackHandler rch) throws SQLException {
		query(db, psc, new RowCallbackHandlerResultSetExtractor(rch));
	}

	public void query(String db, String sql, PreparedStatementSetter pss, RowCallbackHandler rch) throws SQLException {
		query(db, sql, pss, new RowCallbackHandlerResultSetExtractor(rch));
	}

	public void query(String db, String sql, Object[] args, int[] argTypes, RowCallbackHandler rch) throws SQLException {
		query(db, sql, newArgTypePreparedStatementSetter(args, argTypes), rch);
	}

	public void query(String db, String sql, Object[] args, RowCallbackHandler rch) throws SQLException {
		query(db, sql, newArgPreparedStatementSetter(args), rch);
	}

	public void query(String db, String sql, RowCallbackHandler rch, Object... args) throws SQLException {
		query(db, sql, newArgPreparedStatementSetter(args), rch);
	}

	public <T> List<T> query(String db, PreparedStatementCreator psc, RowMapper<T> rowMapper) throws SQLException {
		return query(db, psc, new RowMapperResultSetExtractor<T>(rowMapper));
	}

	public <T> List<T> query(String db, String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws SQLException {
		return query(db, sql, pss, new RowMapperResultSetExtractor<T>(rowMapper));
	}

	public <T> List<T> query(String db, String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper) throws SQLException {
		return query(db, sql, args, argTypes, new RowMapperResultSetExtractor<T>(rowMapper));
	}

	public <T> List<T> query(String db, String sql, Object[] args, RowMapper<T> rowMapper) throws SQLException {
		return query(db, sql, args, new RowMapperResultSetExtractor<T>(rowMapper));
	}

	public <T> List<T> query(String db, String sql, RowMapper<T> rowMapper, Object... args) throws SQLException {
		return query(db, sql, args, new RowMapperResultSetExtractor<T>(rowMapper));
	}

	public <T> T queryForObject(String db, String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper)
			throws SQLException {

		List<T> results = query(db, sql, args, argTypes, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return DataAccessUtils.requiredSingleResult(results);
	}

	public <T> T queryForObject(String db, String sql, Object[] args, RowMapper<T> rowMapper) throws SQLException {
		List<T> results = query(db, sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return DataAccessUtils.requiredSingleResult(results);
	}

	public <T> T queryForObject(String db, String sql, RowMapper<T> rowMapper, Object... args) throws SQLException {
		List<T> results = query(db, sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return DataAccessUtils.requiredSingleResult(results);
	}

	public <T> T queryForObject(String db, String sql, Object[] args, int[] argTypes, Class<T> requiredType)
			throws SQLException {

		return queryForObject(db, sql, args, argTypes, getSingleColumnRowMapper(requiredType));
	}

	public <T> T queryForObject(String db, String sql, Object[] args, Class<T> requiredType) throws SQLException {
		return queryForObject(db, sql, args, getSingleColumnRowMapper(requiredType));
	}

	public <T> T queryForObject(String db, String sql, Class<T> requiredType, Object... args) throws SQLException {
		return queryForObject(db, sql, args, getSingleColumnRowMapper(requiredType));
	}

	public Map<String, Object> queryForMap(String db, String sql, Object[] args, int[] argTypes) throws SQLException {
		return queryForObject(db, sql, args, argTypes, getColumnMapRowMapper());
	}

	public Map<String, Object> queryForMap(String db, String sql, Object... args) throws SQLException {
		return queryForObject(db, sql, args, getColumnMapRowMapper());
	}

	public long queryForLong(String db, String sql, Object[] args, int[] argTypes) throws SQLException {
		Number number = queryForObject(db, sql, args, argTypes, Long.class);
		return (number != null ? number.longValue() : 0);
	}

	public long queryForLong(String db, String sql, Object... args) throws SQLException {
		Number number = queryForObject(db, sql, args, Long.class);
		return (number != null ? number.longValue() : 0);
	}

	public int queryForInt(String db, String sql, Object[] args, int[] argTypes) throws SQLException {
		Number number = queryForObject(db, sql, args, argTypes, Integer.class);
		return (number != null ? number.intValue() : 0);
	}

	public int queryForInt(String db, String sql, Object... args) throws SQLException {
		Number number = queryForObject(db, sql, args, Integer.class);
		return (number != null ? number.intValue() : 0);
	}

	public <T> List<T> queryForList(String db, String sql, Object[] args, int[] argTypes, Class<T> elementType) throws SQLException {
		return query(db, sql, args, argTypes, getSingleColumnRowMapper(elementType));
	}

	public <T> List<T> queryForList(String db, String sql, Object[] args, Class<T> elementType) throws SQLException {
		return query(db, sql, args, getSingleColumnRowMapper(elementType));
	}

	public <T> List<T> queryForList(String db, String sql, Class<T> elementType, Object... args) throws SQLException {
		return query(db, sql, args, getSingleColumnRowMapper(elementType));
	}

	public List<Map<String, Object>> queryForList(String db, String sql, Object[] args, int[] argTypes) throws SQLException {
		return query(db, sql, args, argTypes, getColumnMapRowMapper());
	}

	public List<Map<String, Object>> queryForList(String db, String sql, Object... args) throws SQLException {
		return query(db, sql, args, getColumnMapRowMapper());
	}

	public SqlRowSet queryForRowSet(String db, String sql, Object[] args, int[] argTypes) throws SQLException {
		return query(db, sql, args, argTypes, new SqlRowSetResultSetExtractor());
	}

	public SqlRowSet queryForRowSet(String db, String sql, Object... args) throws SQLException {
		return query(db, sql, args, new SqlRowSetResultSetExtractor());
	}


	public TableSet queryForTableSet(final  String db,
									 PreparedStatementCreator psc, final PreparedStatementSetter pss) throws SQLException {


		return execute(db, psc, new PreparedStatementCallback<TableSet>() {
			public TableSet doInPreparedStatement(PreparedStatement ps) throws SQLException {
				ResultSet rs = null;
				try {
					if (pss != null) {
						pss.setValues(ps);
					}
					rs = ps.executeQuery();
					ResultSet rsToUse = rs;
					if (nativeJdbcExtractor != null) {
						rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
					}
					return new TableSet(rs);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
					if (pss instanceof ParameterDisposer) {
						((ParameterDisposer) pss).cleanupParameters();
					}
				}
			}
		});

	}


	public TableSet queryForTableSet(String db, String sql, Object... args) throws SQLException {
		 return queryForTableSet(db, new SimplePreparedStatementCreator(sql), newArgPreparedStatementSetter(args));
	}



    public void execute(final String db, final String sql) throws SQLException {


        class ExecuteStatementCallback implements StatementCallback<Object> , SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
                stmt.execute(getSql());
                return null;
            }
        }


        execute(db, new ExecuteStatementCallback());

    }



	public <T> T execute(final String db, PreparedStatementCreator psc, PreparedStatementCallback<T> action)
			throws SQLException {

		Assert.notNull(psc, "PreparedStatementCreator must not be null");
		Assert.notNull(action, "Callback object must not be null");
		if (logger.isDebugEnabled()) {
			String sql = getSql(psc);
			logger.debug("Executing prepared SQL statement" + (sql != null ? " [" + sql + "]" : ""));
		}

		Connection con = transactionManager.getConnection(db);
		PreparedStatement ps = null;
		try {
			Connection conToUse = con;
			if (this.nativeJdbcExtractor != null &&
					this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativePreparedStatements()) {
				conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
			}
			ps = psc.createPreparedStatement(conToUse);
			applyStatementSettings(ps);
			PreparedStatement psToUse = ps;
			if (this.nativeJdbcExtractor != null) {
				psToUse = this.nativeJdbcExtractor.getNativePreparedStatement(ps);
			}
			T result = action.doInPreparedStatement(psToUse);
			transactionManager.finishExcute();
			return result;
		}
		catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool deadlock
			// in the case when the exception translator hasn't been initialized yet.
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			String sql = getSql(psc);
			psc = null;
			JdbcUtils.closeStatement(ps);
			ps = null;
			transactionManager.rollback();
			con = null;
			throw ex;
		}
		finally {
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			JdbcUtils.closeStatement(ps);

		}
	}



	public <T> T execute(final String db, StatementCallback<T> action) throws SQLException {
		Assert.notNull(action, "Callback object must not be null");

		Connection con = transactionManager.getConnection(db);
		Statement stmt = null;
		try {
			Connection conToUse = con;
			if (this.nativeJdbcExtractor != null &&
					this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeStatements()) {
				conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
			}
			stmt = conToUse.createStatement();
			applyStatementSettings(stmt);
			Statement stmtToUse = stmt;
			if (this.nativeJdbcExtractor != null) {
				stmtToUse = this.nativeJdbcExtractor.getNativeStatement(stmt);
			}
			T result = action.doInStatement(stmtToUse);
			//handleWarnings(stmt);
			transactionManager.finishExcute();
			return result;
		}
		catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool deadlock
			// in the case when the exception translator hasn't been initialized yet.
			JdbcUtils.closeStatement(stmt);
			stmt = null;
			transactionManager.rollback();
			con = null;
			throw ex;
		}
		finally {
			JdbcUtils.closeStatement(stmt);
		}
	}


	protected void applyStatementSettings(Statement stmt) throws SQLException {
		int fetchSize = getFetchSize();
		if (fetchSize > 0) {
			stmt.setFetchSize(fetchSize);
		}
		int maxRows = getMaxRows();
		if (maxRows > 0) {
			stmt.setMaxRows(maxRows);
		}
		//DataSourceUtils.applyTimeout(stmt, getDataSource(), getQueryTimeout());
		stmt.setQueryTimeout(getQueryTimeout());
	}

	private static String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		}
		else {
			return null;
		}
	}

	/**
	 * Create a new RowMapper for reading columns as key-value pairs.
	 * @return the RowMapper to use
	 * @see org.springframework.jdbc.core.ColumnMapRowMapper
	 */
	protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
		return new ColumnMapRowMapper();
	}

	protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
		return new SingleColumnRowMapper<T>(requiredType);
	}

	protected PreparedStatementSetter newArgPreparedStatementSetter(Object[] args) {
		return new ArgPreparedStatementSetter(args);
	}

	protected PreparedStatementSetter newArgTypePreparedStatementSetter(Object[] args, int[] argTypes) {
		return new ArgTypePreparedStatementSetter(args, argTypes);
	}
	/**
	 * Simple adapter for PreparedStatementCreator, allowing to use a plain SQL statement.
	 */
	private static class SimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

		private final String sql;

		public SimplePreparedStatementCreator(String sql) {
			Assert.notNull(sql, "SQL must not be null");
			this.sql = sql;
		}

		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			return con.prepareStatement(this.sql);
		}

		public String getSql() {
			return this.sql;
		}
	}


	/**
	 * Simple adapter for CallableStatementCreator, allowing to use a plain SQL statement.
	 */
	private static class SimpleCallableStatementCreator implements CallableStatementCreator, SqlProvider {

		private final String callString;

		public SimpleCallableStatementCreator(String callString) {
			Assert.notNull(callString, "Call string must not be null");
			this.callString = callString;
		}

		public CallableStatement createCallableStatement(Connection con) throws SQLException {
			return con.prepareCall(this.callString);
		}

		public String getSql() {
			return this.callString;
		}
	}


	/**
	 * Adapter to enable use of a RowCallbackHandler inside a ResultSetExtractor.
	 * <p>Uses a regular ResultSet, so we have to be careful when using it:
	 * We don't use it for navigating since this could lead to unpredictable consequences.
	 */
	private static class RowCallbackHandlerResultSetExtractor implements ResultSetExtractor<Object> {

		private final RowCallbackHandler rch;

		public RowCallbackHandlerResultSetExtractor(RowCallbackHandler rch) {
			this.rch = rch;
		}

		public Object extractData(ResultSet rs) throws SQLException {
			while (rs.next()) {
				this.rch.processRow(rs);
			}
			return null;
		}
	}



	class ArgTypePreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer {

		private final Object[] args;

		private final int[] argTypes;


		/**
		 * Create a new ArgTypePreparedStatementSetter for the given arguments.
		 * @param args the arguments to set
		 * @param argTypes the corresponding SQL types of the arguments
		 */
		public ArgTypePreparedStatementSetter(Object[] args, int[] argTypes) {
			if ((args != null && argTypes == null) || (args == null && argTypes != null) ||
					(args != null && args.length != argTypes.length)) {
				throw new InvalidDataAccessApiUsageException("args and argTypes parameters must match");
			}
			this.args = args;
			this.argTypes = argTypes;
		}


		public void setValues(PreparedStatement ps) throws SQLException {
			int parameterPosition = 1;
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					Object arg = this.args[i];
					if (arg instanceof Collection && this.argTypes[i] != Types.ARRAY) {
						Collection entries = (Collection) arg;
						for (Iterator it = entries.iterator(); it.hasNext();) {
							Object entry = it.next();
							if (entry instanceof Object[]) {
								Object[] valueArray = ((Object[])entry);
								for (int k = 0; k < valueArray.length; k++) {
									Object argValue = valueArray[k];
									doSetValue(ps, parameterPosition, this.argTypes[i], argValue);
									parameterPosition++;
								}
							}
							else {
								doSetValue(ps, parameterPosition, this.argTypes[i], entry);
								parameterPosition++;
							}
						}
					}
					else {
						doSetValue(ps, parameterPosition, this.argTypes[i], arg);
						parameterPosition++;
					}
				}
			}
		}

		/**
		 * Set the value for the prepared statement's specified parameter position using the passed in
		 * value and type. This method can be overridden by sub-classes if needed.
		 * @param ps the PreparedStatement
		 * @param parameterPosition index of the parameter position
		 * @param argType the argument type
		 * @param argValue the argument value
		 * @throws java.sql.SQLException
		 */
		protected void doSetValue(PreparedStatement ps, int parameterPosition, int argType, Object argValue)
				throws SQLException {
			StatementCreatorUtils.setParameterValue(ps, parameterPosition, argType, argValue);
		}

		public void cleanupParameters() {
			StatementCreatorUtils.cleanupParameters(this.args);
		}

	}



	class ArgPreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer {

		private final Object[] args;


		/**
		 * Create a new ArgPreparedStatementSetter for the given arguments.
		 * @param args the arguments to set
		 */
		public ArgPreparedStatementSetter(Object[] args) {
			this.args = args;
		}


		public void setValues(PreparedStatement ps) throws SQLException {
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					Object arg = this.args[i];
					doSetValue(ps, i + 1, arg);
				}
			}
		}

		/**
		 * Set the value for prepared statements specified parameter index using the passed in value.
		 * This method can be overridden by sub-classes if needed.
		 * @param ps the PreparedStatement
		 * @param parameterPosition index of the parameter position
		 * @param argValue the value to set
		 * @throws java.sql.SQLException
		 */
		protected void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
			if (argValue instanceof SqlParameterValue) {
				SqlParameterValue paramValue = (SqlParameterValue) argValue;
				StatementCreatorUtils.setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
			}
			else {
				StatementCreatorUtils.setParameterValue(ps, parameterPosition, SqlTypeValue.TYPE_UNKNOWN, argValue);
			}
		}

		public void cleanupParameters() {
			StatementCreatorUtils.cleanupParameters(this.args);
		}

	}
}


