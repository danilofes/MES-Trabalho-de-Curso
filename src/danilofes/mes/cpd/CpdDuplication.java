package danilofes.mes.cpd;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class CpdDuplication {

	@XmlAttribute
	public int lines;
	@XmlAttribute
	public int tokens;
	
	@XmlElement(name="file")
	public Collection<CpdCodeFragment> fragments;

}
