package danilofes.mes;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import danilofes.mes.cpd.CpdResult;
import danilofes.mes.simian.SimianResult;

public class ParseFiles {

	public static void main(String[] args) throws Exception {
//		File cpdfile = new File("data/seed-cpd-25tkn.xml");
//		CpdResult cpdResult = JAXB.unmarshal(cpdfile, CpdResult.class);
//
//		printResultSummary(cpdResult.duplications);
//		printResult(cpdResult.duplications);

		File simianfile = new File("data/seed-simian-3loc.xml");
		SimianResult simianResult = JAXB.unmarshal(simianfile, SimianResult.class);

		printResultSummary(simianResult.duplications);
//		printResult(simianResult.duplications);
	}

	private static void printResult(List<? extends Duplication> duplications) {
		for (Duplication duplication : duplications) {
			System.out.println(duplication.getLines() + " duplicated lines.");
			for (CodeFragment fragment : duplication.getCodeFragments()) {
				System.out.println(fragment.getFilePath() + ": line " + fragment.getLine());
			}
		}
	}

	private static void printResultSummary(List<? extends Duplication> duplications) {
		
		int cloc = 0;
		for (Duplication duplication : duplications) {
			cloc += duplication.getLines() * duplication.getCodeFragments().size(); 
		}
		System.out.println(duplications.size() + " duplications");
		System.out.println(cloc + " cloc");
	}
	
}
