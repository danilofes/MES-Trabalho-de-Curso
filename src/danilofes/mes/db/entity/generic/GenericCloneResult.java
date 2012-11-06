package danilofes.mes.db.entity.generic;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import danilofes.mes.db.entity.CloneResult;

@XmlRootElement(name = "pmd-cpd")
public class GenericCloneResult extends CloneResult<GenericDuplication> {

	@Override
	public List<GenericDuplication> getDuplications() {
		return this.duplications;
	}

	@XmlElement(name = "duplication")
	@Override
	public void setDuplications(List<GenericDuplication> duplications) {
		this.duplications = duplications;
	}

}
