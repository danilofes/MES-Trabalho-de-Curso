package danilofes.mes.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import danilofes.mes.db.DBFactory;
import danilofes.mes.db.entity.Fragment;
import danilofes.mes.db.entity.generic.GenericFragment;

public class FragmentDAO extends DBFactory {

	public Integer create(Fragment fragment, Integer duplicationId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("Fragment(duplicationId, line, path) values(?, ?, ?)");
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.setInt(1, duplicationId);
		statement.setInt(2, fragment.getLine());
		statement.setString(3, fragment.getPath());

		this.log(sql.toString());
		statement.executeUpdate();

		return this.getLastInsertedId("Fragment");
	}

	public int getFragmentsCountByDuplicationId(Integer duplicationId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(f.id) from ");
		sql.append(DBFactory.SCHEMA);
		sql.append(".");
		sql.append("Fragment f ");
		sql.append("where f.duplicationId = ?");

		PreparedStatement statement;
		ResultSet result = null;

		statement = CONNECTION.prepareStatement(sql.toString());
		statement.setInt(1, duplicationId);

		result = statement.executeQuery();
		return result != null && result.next() ? result.getInt(1) : 0;
	}

	public List<GenericFragment> findByCloneResultName(String cloneResultName) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("f.id ");
		sql.append(",f.duplicationId ");
		sql.append(",f.line ");
		sql.append(",f.path ");
		sql.append("from dcc890.Fragment f ");
		sql.append("join dcc890.Duplication d ");
		sql.append("on d.id = f.duplicationId ");
		sql.append("join dcc890.CloneResult c ");
		sql.append("on c.id = d.cloneResultId ");
		sql.append("where c.appname = ?");
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.setString(1, cloneResultName);
		
		ResultSet result = statement.executeQuery();

		return this.parse(result);
	}

	public List<GenericFragment> findByDuplication(int duplicationId) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("f.id ");
		sql.append(",f.duplicationId ");
		sql.append(",f.line ");
		sql.append(",f.path ");
		sql.append("from dcc890.Fragment f ");
		sql.append("where f.duplicationId = ?");
		PreparedStatement statement = CONNECTION.prepareStatement(sql.toString());
		statement.setInt(1, duplicationId);

		ResultSet result = statement.executeQuery();

		return this.parse(result);
	}

	private List<GenericFragment> parse(ResultSet result) throws SQLException {
		List<GenericFragment> list = new ArrayList<GenericFragment>();
		if (result != null) {
			while (result.next()) {
				GenericFragment fragment = new GenericFragment();
				int id = result.getInt("id");
				fragment.setId(id);
				fragment.setLine(result.getInt("line"));
				fragment.setDuplicationId(result.getInt("duplicationId"));
				fragment.setPath(result.getString("path"));

				list.add(fragment);
			}
			result.close();
		}

		return list;
	}

	public void clear() throws SQLException {
		super.clearRows("Fragment");
	}

}
