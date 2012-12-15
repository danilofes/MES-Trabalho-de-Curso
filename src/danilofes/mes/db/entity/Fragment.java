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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + line;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fragment other = (Fragment) obj;
		if (line != other.line)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
}
