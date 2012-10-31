package danilofes.mes.cpd;

import javax.xml.bind.annotation.XmlAttribute;

import danilofes.mes.CodeFragment;

public class CpdCodeFragment implements CodeFragment {

	@XmlAttribute
	public int line;
	@XmlAttribute
	public String path;

	@Override
	public String getFilePath() {
		return this.path;
	}

	@Override
	public int getLine() {
		return this.line;
	}

}
