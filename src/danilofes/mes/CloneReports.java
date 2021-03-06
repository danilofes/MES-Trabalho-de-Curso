package danilofes.mes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import danilofes.mes.db.dao.CloneResultDAO;
import danilofes.mes.db.dao.DuplicationDAO;
import danilofes.mes.db.dao.FragmentDAO;
import danilofes.mes.db.entity.generic.GenericCloneResult;
import danilofes.mes.db.entity.generic.GenericDuplication;
import danilofes.mes.db.entity.generic.GenericFragment;
import danilofes.mes.db.matrix.CloneIntersectionMatrix;
import danilofes.mes.db.matrix.CloneMatrixWrapper;

public class CloneReports {
	private static final double SEED_TOTAL_LOC = 169333.0;// 22296 + 55461 +
															// 91576 = 169333;
	private static CloneResultDAO resultDao = new CloneResultDAO();
	private static FragmentDAO fragmentDAO = new FragmentDAO();
	private static DuplicationDAO duplicationDAO = new DuplicationDAO();

	public void printResultSummary() throws Exception {
		List<GenericCloneResult> list = resultDao.list();
		for (GenericCloneResult cloneResult : list) {
			System.out.println(String.format("Aplicativo: %s", cloneResult.getAppName()));
			int cloc = 0;
			List<GenericDuplication> duplications = cloneResult.getDuplications();
			for (GenericDuplication duplication : duplications) {
				List<GenericFragment> fragments = duplication.getFragments();
				// System.out.println(fragments.get(0).getPath());
				cloc += duplication.getLines() * (fragments.size() - 1);
			}
			System.out.print(duplications.size() + " duplications, ");
			System.out.print(cloc + " cloc");
			System.out.print(" (" + (100.0 * (double) cloc / SEED_TOTAL_LOC) + " %)");
			System.out.println();
		}
	}

	/**
	 * Proporção de linhas duplicadas detectadas pelos apps.
	 * 
	 * @throws Exception
	 */
	public void duplicationLOCProportion() throws Exception {
		List<GenericDuplication> duplications = duplicationDAO.list();
		Map<Integer, Integer> proportion = new HashMap<Integer, Integer>();
		for (GenericDuplication duplication : duplications) {
			if (proportion.containsKey(duplication.getLines())) {
				Integer value = proportion.get(duplication.getLines());
				proportion.put(duplication.getLines(), ++value);
			} else {
				proportion.put(duplication.getLines(), 1);
			}
		}
		System.out.println(proportion.toString());
	}

	public void similarFragmentsCloneDetectionFound() throws Exception {
		List<GenericFragment> cloneDiggerFragments = fragmentDAO.findByCloneResultName(ParseFiles.CLONEDIGGER);
		List<GenericFragment> simianFragments = fragmentDAO.findByCloneResultName(ParseFiles.SIMIAN);
		List<GenericFragment> cpdFragments = fragmentDAO.findByCloneResultName(ParseFiles.CPD);

		CloneMatrixWrapper wrapper = new CloneMatrixWrapper();
		wrapper.addFragmentList(cloneDiggerFragments, ParseFiles.CLONEDIGGER);
		wrapper.addFragmentList(simianFragments, ParseFiles.SIMIAN);
		wrapper.addFragmentList(cpdFragments, ParseFiles.CPD);

		printResultIntersections(wrapper);
	}

	private void printResultIntersections(CloneMatrixWrapper wrapper) {
		Iterator<Entry<String, Map<Integer, CloneIntersectionMatrix>>> iterator = wrapper.getMatrix().entrySet().iterator();

		int cloneDiggerAndSimianIntersectionCount = 0;
		int cloneDiggerAndCpdIntersectionCount = 0;
		int simianAndCpdIntersectionCount = 0;
		int justSimianCount = 0;
		int justCpdCount = 0;
		int justCloneDiggerCount = 0;
		int fullIntersectionCount = 0;
		int simianCount = 0;
		int cpdCount = 0;
		int cloneDiggerCount = 0;

		while (iterator.hasNext()) {
			Entry<String, Map<Integer, CloneIntersectionMatrix>> entry = iterator.next();
			Collection<CloneIntersectionMatrix> fragments = entry.getValue().values();
			for (CloneIntersectionMatrix fragment : fragments) {
				if (fragment.cloneDigger && fragment.cpd && fragment.simian) {
					fullIntersectionCount++;
				} else if (fragment.cloneDigger && fragment.cpd && !fragment.simian) {
					cloneDiggerAndCpdIntersectionCount++;
				} else if (fragment.cloneDigger && !fragment.cpd && fragment.simian) {
					cloneDiggerAndSimianIntersectionCount++;
				} else if (!fragment.cloneDigger && fragment.cpd && fragment.simian) {
					simianAndCpdIntersectionCount++;
				} else if (!fragment.cloneDigger && !fragment.cpd && fragment.simian) {
					justSimianCount++;
				} else if (fragment.cloneDigger && !fragment.cpd && !fragment.simian) {
					justCloneDiggerCount++;
				} else if (!fragment.cloneDigger && fragment.cpd && !fragment.simian) {
					justCpdCount++;
				} else {
					System.out.println(String.format("Houston! We have a problem! [%s]", entry.getKey()));
				}
				
				if (fragment.cloneDigger) {
					cloneDiggerCount++;
				} 
				if (fragment.cpd) {
					cpdCount++;
				}
				if (fragment.simian) {
					simianCount++;
				} 
				
			}

		}
		System.out.println(String.format("Total Clone Digger : [%s] - [%.1f]%%", cloneDiggerCount, percentageOfSeedLoc(cloneDiggerCount)));
		System.out.println(String.format("Total Simian : [%s] - [%.1f]%%", simianCount, percentageOfSeedLoc(simianCount)));
		System.out.println(String.format("Total CPD : [%s] - [%.1f]%%", cpdCount, percentageOfSeedLoc(cpdCount)));

		System.out.println(String.format("Somente Clone Digger : [%s] - [%.1f]%%", justCloneDiggerCount, percentageOfSeedLoc(justCloneDiggerCount)));
		System.out.println(String.format("Somente Simian : [%s] - [%.1f]%%", justSimianCount, percentageOfSeedLoc(justSimianCount)));
		System.out.println(String.format("Somente CPD : [%s] - [%.1f]%%", justCpdCount, percentageOfSeedLoc(justCpdCount)));

		System.out.println(String.format("Interseção : Clone Digger e CPD : [%s] - [%.1f]%%", cloneDiggerAndCpdIntersectionCount,
				percentageOfSeedLoc(cloneDiggerAndCpdIntersectionCount)));
		System.out.println(String.format("Interseção : Clone Digger e Simian : [%s] - [%.1f]%%", cloneDiggerAndSimianIntersectionCount,
				percentageOfSeedLoc(cloneDiggerAndSimianIntersectionCount)));
		System.out.println(String.format("Interseção : Simian e CPD : [%s] - [%.1f]%%", simianAndCpdIntersectionCount,
				percentageOfSeedLoc(simianAndCpdIntersectionCount)));
		System.out.println(String.format("Interseção : Todos : [%s] - [%.1f]%%", fullIntersectionCount, percentageOfSeedLoc(fullIntersectionCount)));
	}

	private Double percentageOfSeedLoc(int loc) {
		return loc * 100 / SEED_TOTAL_LOC;
	}
}
