package danilofes.mes.simian;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="simian")
public class SimianResult {

	@XmlElementWrapper(name="check")
	@XmlElement(name="set")
	public List<SimianDuplication> duplications;

}
