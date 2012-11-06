package danilofes.mes.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import danilofes.mes.db.DBFactory;
import danilofes.mes.db.entity.CloneResult;
import danilofes.mes.db.entity.Duplication;
import danilofes.mes.db.entity.generic.GenericCloneResult;

public class CloneResultDAO extends DBFactory {

	public Integer create(CloneResult<? extends Duplication<?>> cloneResult) {
		Integer id = null;
		PreparedStatement statement = null;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("CloneResult(appName) values(?)");
		try {
			CONNECTION.setAutoCommit(false);
			statement = CONNECTION.prepareStatement(sql.toString());
			statement.setString(1, cloneResult.getAppName());
			System.out.println(sql.toString());
			statement.executeUpdate();
			id = this.getLastInsertedId("CloneResult");
			this.insertDuplications(cloneResult.getDuplications(), id);
			CONNECTION.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				statement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	public boolean insertDuplications(List<? extends Duplication<?>> duplications, Integer cloneResultId) throws SQLException {
		DuplicationDAO duplicationDAO = new DuplicationDAO();
		for (Duplication<?> duplication : duplications) {
			duplicationDAO.create(duplication, cloneResultId);
		}

		return true;
	}

	public List<GenericCloneResult> list() throws Exception {
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("c.id");
		sql.append(",c.appName ");
		sql.append("from dcc890.CloneResult c");

		PreparedStatement statement;
		ResultSet result = null;

		statement = CONNECTION.prepareStatement(sql.toString());

		result = statement.executeQuery();

		return this.parse(result);
	}

	private List<GenericCloneResult> parse(ResultSet result) throws Exception {
		List<GenericCloneResult> list = new ArrayList<GenericCloneResult>();
		DuplicationDAO dao = new DuplicationDAO();
		if (result != null) {
			while (result.next()) {
				GenericCloneResult clone = new GenericCloneResult();
				int id = result.getInt("id");
				clone.setId(id);
				clone.setAppName(result.getString("appName"));
				clone.setDuplications(dao.findByCloneResult(id));

				list.add(clone);
			}
		}
		return list;
	}
}
