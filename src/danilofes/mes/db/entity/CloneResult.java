package danilofes.mes.db.entity;

import java.util.List;

public abstract class CloneResult<T extends Duplication<?>> {
	private Integer id;
	protected String appName;
	protected List<T> duplications;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public abstract List<T> getDuplications();

	public abstract void setDuplications(List<T> duplications);

}
