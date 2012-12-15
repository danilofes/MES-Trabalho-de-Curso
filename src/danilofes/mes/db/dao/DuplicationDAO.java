package danilofes.mes.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import danilofes.mes.db.DBFactory;
import danilofes.mes.db.entity.Duplication;
import danilofes.mes.db.entity.Fragment;
import danilofes.mes.db.entity.generic.GenericDuplication;

public class DuplicationDAO extends DBFactory {

	public Integer create(Duplication duplication, Integer cloneResultId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("Duplication(cloneResultId, lines) values(?, ?)");

		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, cloneResultId);
		statement.setInt(2, duplication.getLines());
		this.log(sql.toString());
		statement.executeUpdate();
		Integer id = this.getLastInsertedId("Duplication");

		this.insertFragments(duplication.getFragments(), id);

		return id;
	}

	public boolean insertFragments(List<Fragment> fragments, Integer duplicationId) throws SQLException {
		FragmentDAO fragmentDAO = new FragmentDAO();
		for (Fragment fragment : fragments) {
			fragmentDAO.create(fragment, duplicationId);
		}
		return true;
	}

	public int getDuplicationCountByAppName(String appName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(d.id) from ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("CloneResult c join ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("Duplication d join ");
		sql.append("on c.id = d.cloneResultId ");
		sql.append("where c.appName = ?");

		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.setString(1, appName);

		ResultSet result = statement.executeQuery();

		return result != null && result.next() ? result.getInt(1) : 0;
	}
	
	public List<GenericDuplication> list() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("d.id ");
		sql.append(",d.lines ");
		sql.append(",d.cloneResultId ");
		sql.append("from dcc890.Duplication d ");
		sql.append("order by  d.lines");
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());

		ResultSet result = statement.executeQuery();

		return this.parse(result);
	}

	public List<GenericDuplication> findByCloneResult(Integer cloneResultId) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("d.id ");
		sql.append(",d.lines ");
		sql.append(",d.cloneResultId ");
		sql.append("from dcc890.Duplication d ");
		sql.append("where d.cloneResultId = ?");
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.setInt(1, cloneResultId);

		ResultSet result = statement.executeQuery();

		return this.parse(result);
	}

	private List<GenericDuplication> parse(ResultSet result) throws SQLException {
		List<GenericDuplication> list = new ArrayList<GenericDuplication>();
		FragmentDAO dao = new FragmentDAO();
		if (result != null) {
			while (result.next()) {
				GenericDuplication duplication = new GenericDuplication();
				int id = result.getInt("id");
				duplication.setId(id);
				duplication.setLines(result.getInt("lines"));
				duplication.setCloneResultId(result.getInt("cloneResultId"));
				duplication.setFragments(dao.findByDuplication(id));

				list.add(duplication);
			}
			result.close();
		}
		return list;
	}
	
	public void clear() throws SQLException {
		super.clearRows("Duplication");
	}

}
