package danilofes.mes.db.entity.simian;

import javax.xml.bind.annotation.XmlAttribute;

import danilofes.mes.db.entity.Fragment;

public class SimianFragment extends Fragment {

	@XmlAttribute(name = "startLineNumber")
	@Override
	public void setLine(int line) {
		this.line = line;

	}

	@XmlAttribute(name = "sourceFile")
	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int getLine() {
		return this.line;
	}

	@Override
	public String getPath() {
		return this.path;
	}

}
