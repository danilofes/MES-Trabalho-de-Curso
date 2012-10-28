package danilofes.mes;

import java.io.File;

import javax.xml.bind.JAXB;

import danilofes.mes.cpd.CpdResult;

public class ParseFiles {

	public static void main(String[] args) throws Exception {
		File file = new File("data/pmd-cpd.xml");
		
		CpdResult result = JAXB.unmarshal(file, CpdResult.class);
		
		JAXB.marshal(result, System.out);

	}

}
