package danilofes.mes.db.entity.simian;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import danilofes.mes.db.entity.CloneResult;

@XmlRootElement(name = "simian")
public class SimianCloneResult extends CloneResult<SimianDuplication> {

	@XmlElementWrapper(name = "check")
	@XmlElement(name = "set")
	@Override
	public void setDuplications(List<SimianDuplication> duplications) {
		this.duplications = duplications;
	}

	@Override
	public List<SimianDuplication> getDuplications() {
		return this.duplications;
	}
}
