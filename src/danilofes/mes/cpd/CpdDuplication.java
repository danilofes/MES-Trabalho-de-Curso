package danilofes.mes.cpd;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import danilofes.mes.CodeFragment;
import danilofes.mes.Duplication;

public class CpdDuplication implements Duplication {

	@XmlAttribute
	public int lines;
	@XmlAttribute
	public int tokens;
	
	@XmlElement(name="file")
	public List<CpdCodeFragment> fragments;

	@Override
	public int getLines() {
		return this.lines;
	}

	@Override
	public List<? extends CodeFragment> getCodeFragments() {
		return this.fragments;
	}

}
