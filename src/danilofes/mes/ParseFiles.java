package danilofes.mes;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import danilofes.mes.db.dao.CloneResultDAO;
import danilofes.mes.db.entity.generic.GenericCloneResult;
import danilofes.mes.db.entity.generic.GenericDuplication;
import danilofes.mes.db.entity.simian.SimianCloneResult;

public class ParseFiles {

	private static CloneResultDAO resultDao = new CloneResultDAO();

	public static void main(String[] args) throws Exception {

		// createSimianResults();
		//
		// createCpdResults();
		//
		// createCloneDiggerResult();
		printResultSummary();

	}

	private static void createCloneDiggerResult() {
		File cloneDiggerFile = new File("data/clonedigger.xml");
		GenericCloneResult cloneDiggerResult = JAXB.unmarshal(cloneDiggerFile, GenericCloneResult.class);
		cloneDiggerResult.setAppName("clonedigger");
		resultDao.create(cloneDiggerResult);
	}

	private static void createCpdResults() {
		File cpdfile = new File("data/cpd.xml");
		GenericCloneResult cpdResult = JAXB.unmarshal(cpdfile, GenericCloneResult.class);
		cpdResult.setAppName("cpd-pmd");
		resultDao.create(cpdResult);
	}

	private static void createSimianResults() {
		File simianfile = new File("data/seed-simian-3loc.xml");
		SimianCloneResult simianResult = JAXB.unmarshal(simianfile, SimianCloneResult.class);
		simianResult.setAppName("simian-result");
		resultDao.create(simianResult);
	}

	private static void printResultSummary() throws Exception {
		List<GenericCloneResult> list = resultDao.list();
		for (GenericCloneResult cloneResult : list) {
			System.out.println(String.format("Aplicativo: %s", cloneResult.getAppName()));
			int cloc = 0;
			List<GenericDuplication> duplications = cloneResult.getDuplications();
			for (GenericDuplication duplication : duplications) {
				cloc += duplication.getLines() * (duplication.getFragments().size() - 1);
			}
			System.out.print(duplications.size() + " duplications, ");
			System.out.print(cloc + " cloc");
			System.out.print(" (" + (100.0 * (double) cloc / 174388.0) + " %)");
			System.out.println();
		}
	}
}
