package danilofes.mes.db.entity;

import java.util.List;

public abstract class Duplication<T extends Fragment> {
	private Integer id;
	private Integer cloneResultId;
	protected int lines;
	protected List<T> fragments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCloneResultId() {
		return cloneResultId;
	}

	public void setCloneResultId(Integer cloneResultId) {
		this.cloneResultId = cloneResultId;
	}

	public abstract List<T> getFragments();

	public abstract int getLines();

	public abstract void setLines(int lines);

	public abstract void setFragments(List<T> fragments);
}
