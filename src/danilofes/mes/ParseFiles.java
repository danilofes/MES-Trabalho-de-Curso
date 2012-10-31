package danilofes.mes;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import danilofes.mes.cpd.CpdResult;
import danilofes.mes.simian.SimianResult;

public class ParseFiles {

	public static void main(String[] args) throws Exception {
		File cpdfile = new File("data/pmd-cpd.xml");
		CpdResult cpdResult = JAXB.unmarshal(cpdfile, CpdResult.class);

		printResult(cpdResult.duplications);

		File simianfile = new File("data/simian.xml");
		SimianResult simianResult = JAXB.unmarshal(simianfile, SimianResult.class);

		printResult(simianResult.duplications);
	}

	private static void printResult(List<? extends Duplication> duplications) {
		for (Duplication duplication : duplications) {
			System.out.println(duplication.getLines() + " duplicated lines.");
			for (CodeFragment fragment : duplication.getCodeFragments()) {
				System.out.println(fragment.getFilePath() + ": line " + fragment.getLine());
			}
		}
	}
	
}
