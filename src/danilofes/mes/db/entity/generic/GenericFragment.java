package danilofes.mes.db.entity.generic;

import javax.xml.bind.annotation.XmlAttribute;

import danilofes.mes.db.entity.Fragment;

public class GenericFragment extends Fragment {

	@Override
	public int getLine() {
		return this.line;
	}

	@XmlAttribute(name = "line")
	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@XmlAttribute(name = "path")
	@Override
	public void setPath(String path) {
		this.path = path;

	}

}
