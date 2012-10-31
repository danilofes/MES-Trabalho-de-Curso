package danilofes.mes.simian;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import danilofes.mes.CodeFragment;
import danilofes.mes.Duplication;

public class SimianDuplication implements Duplication {

	@XmlAttribute
	public int lineCount;
	
	@XmlElement(name="block")
	public List<SimianCodeFragment> fragments;

	@Override
	public int getLines() {
		return this.lineCount;
	}

	@Override
	public List<? extends CodeFragment> getCodeFragments() {
		return this.fragments;
	}

}
