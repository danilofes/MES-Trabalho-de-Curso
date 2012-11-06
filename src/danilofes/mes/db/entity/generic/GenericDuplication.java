package danilofes.mes.db.entity.generic;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import danilofes.mes.db.entity.Duplication;

public class GenericDuplication extends Duplication<GenericFragment> {

	@XmlAttribute(name = "lines")
	@Override
	public void setLines(int lines) {
		this.lines = lines;

	}

	@XmlElement(name = "file")
	@Override
	public void setFragments(List<GenericFragment> fragments) {
		this.fragments = fragments;

	}

	@Override
	public int getLines() {
		return this.lines;
	}

	@Override
	public List<GenericFragment> getFragments() {
		return this.fragments;
	}

}
