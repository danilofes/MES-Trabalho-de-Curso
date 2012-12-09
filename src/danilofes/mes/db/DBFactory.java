package danilofes.mes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFactory {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String HOST = "jdbc:derby://localhost:1527/";
	public static final String SCHEMA = "dcc890";
	protected static Connection CONNECTION = null;

	public DBFactory() {
		if (CONNECTION == null) {
			connect();
		}
	}

	private void connect() {
		try {
			Class.forName(DRIVER).newInstance();
			this.CONNECTION = DriverManager.getConnection(HOST + SCHEMA);
			System.out.println(String.format("Connected at %s%s", HOST, SCHEMA));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Integer getLastInsertedId(String tableName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MAX(id) from ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append(tableName);
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		ResultSet result = statement.executeQuery();

		return result != null && result.next() ? result.getInt(1) : null;
	}
	
	protected void clearRows(String tableName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append(tableName);
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.executeUpdate();
	}

	protected void log(String sql) {
		//System.out.println(sql);
	}
	
}
