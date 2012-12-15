package danilofes.mes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import danilofes.mes.db.dao.CloneResultDAO;
import danilofes.mes.db.dao.DuplicationDAO;
import danilofes.mes.db.dao.FragmentDAO;
import danilofes.mes.db.entity.generic.GenericCloneResult;
import danilofes.mes.db.entity.generic.GenericDuplication;
import danilofes.mes.db.entity.generic.GenericFragment;
import danilofes.mes.db.matrix.CloneMatrixWrapper;

public class CloneReports {
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
			final double totalLoc = 169333.0; // 22296 + 55461 + 91576 = 169333;
			System.out.print(" (" + (100.0 * (double) cloc / totalLoc) + " %)");
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

		System.out.println(wrapper.getMatrix());

	}

}
