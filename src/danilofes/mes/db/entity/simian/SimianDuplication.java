package danilofes.mes.db.entity.simian;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import danilofes.mes.db.entity.Duplication;

public class SimianDuplication extends Duplication<SimianFragment> {

	@XmlAttribute(name = "lineCount")
	@Override
	public void setLines(int lines) {
		this.lines = lines;
	}

	@XmlElement(name = "block")
	@Override
	public void setFragments(List<SimianFragment> fragments) {
		this.fragments = fragments;
	}

	@Override
	public List<SimianFragment> getFragments() {
		return this.fragments;
	}

	@Override
	public int getLines() {
		return this.lines;
	}

}
