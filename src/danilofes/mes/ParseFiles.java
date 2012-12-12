package danilofes.mes;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXB;

import danilofes.mes.db.dao.CloneResultDAO;
import danilofes.mes.db.dao.DuplicationDAO;
import danilofes.mes.db.dao.FragmentDAO;
import danilofes.mes.db.entity.generic.GenericCloneResult;
import danilofes.mes.db.entity.generic.GenericDuplication;
import danilofes.mes.db.entity.generic.GenericFragment;
import danilofes.mes.db.entity.simian.SimianCloneResult;

public class ParseFiles {

	private static CloneResultDAO resultDao = new CloneResultDAO();

	public static void main(String[] args) throws Exception {

		/*
		clearDb();
		createSimianResults();
		createCpdResults();
		createCloneDiggerResult();
		*/
		
		printResultSummary();
	}

	private static void createCloneDiggerResult() {
		File cloneDiggerFile = new File("data/seed-clonedigger.xml");
		GenericCloneResult cloneDiggerResult = JAXB.unmarshal(cloneDiggerFile, GenericCloneResult.class);
		cloneDiggerResult.setAppName("clonedigger");
		resultDao.create(cloneDiggerResult);
		System.out.println("Clone digger file processed.");
	}

	private static void createCpdResults() {
		File cpdfile = new File("data/seed-cpd-25tkn.xml");
		GenericCloneResult cpdResult = JAXB.unmarshal(cpdfile, GenericCloneResult.class);
		cpdResult.setAppName("cpd");
		resultDao.create(cpdResult);
		System.out.println("CPD file processed.");
	}

	private static void createSimianResults() {
		File simianfile = new File("data/seed-simian-3loc.xml");
		SimianCloneResult simianResult = JAXB.unmarshal(simianfile, SimianCloneResult.class);
		simianResult.setAppName("simian");
		resultDao.create(simianResult);
		System.out.println("Simian file processed.");
	}

	private static void printResultSummary() throws Exception {
		List<GenericCloneResult> list = resultDao.list();
		for (GenericCloneResult cloneResult : list) {
			System.out.println(String.format("Aplicativo: %s", cloneResult.getAppName()));
			int cloc = 0;
			List<GenericDuplication> duplications = cloneResult.getDuplications();
			for (GenericDuplication duplication : duplications) {
				List<GenericFragment> fragments = duplication.getFragments();
				//System.out.println(fragments.get(0).getPath());
				cloc += duplication.getLines() * (fragments.size() - 1);
			}
			System.out.print(duplications.size() + " duplications, ");
			System.out.print(cloc + " cloc");
			System.out.print(" (" + (100.0 * (double) cloc / 174388.0) + " %)");
			System.out.println();
		}
	}
	
	private static void clearDb() throws SQLException {
		new FragmentDAO().clear();
		new DuplicationDAO().clear();
		new CloneResultDAO().clear();
		System.out.println("DB clear");
	}
	
}
