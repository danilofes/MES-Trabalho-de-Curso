package danilofes.mes.cpd;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pmd-cpd")
public class CpdResult {

	@XmlElement(name="duplication")
	public List<CpdDuplication> duplications;

}
