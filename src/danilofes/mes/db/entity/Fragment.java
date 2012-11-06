package danilofes.mes.db.entity;

public abstract class Fragment {
	private Integer id;
	private Integer duplicationId;
	protected int line;
	protected String path;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDuplicationId() {
		return duplicationId;
	}

	public void setDuplicationId(Integer duplicationId) {
		this.duplicationId = duplicationId;
	}

	public abstract int getLine();

	public abstract void setLine(int line);

	public abstract String getPath();

	public abstract void setPath(String path);
}
