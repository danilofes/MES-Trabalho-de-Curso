package danilofes.mes.simian;

import javax.xml.bind.annotation.XmlAttribute;

import danilofes.mes.CodeFragment;

public class SimianCodeFragment implements CodeFragment {

	@XmlAttribute
	public int startLineNumber;
	@XmlAttribute
	public String sourceFile;

	@Override
	public String getFilePath() {
		return this.sourceFile;
	}

	@Override
	public int getLine() {
		return this.startLineNumber;
	}

}
