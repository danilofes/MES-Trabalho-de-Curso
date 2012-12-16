package danilofes.mes.db.matrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import danilofes.mes.ParseFiles;
import danilofes.mes.db.entity.generic.GenericFragment;

public class CloneMatrixWrapper {
	private Map<String, Map<Integer, CloneIntersectionMatrix>> matrix = new HashMap<String, Map<Integer, CloneIntersectionMatrix>>();

	public void addFragmentList(List<GenericFragment> fragments, String appName) {

		for (GenericFragment fragment : fragments) {
			Map<Integer, CloneIntersectionMatrix> matrixIntersections = matrix.get(fragment.getPath());

			if (matrixIntersections == null) {
				matrixIntersections = new HashMap<Integer, CloneIntersectionMatrix>();
			}

			addAllLines(matrixIntersections, fragment, appName);

			matrix.put(fragment.getPath(), matrixIntersections);
		}

	}

	private void addAllLines(Map<Integer, CloneIntersectionMatrix> matrixIntersections, GenericFragment fragment, String appName) {
		for (int currentLine = fragment.getLine(); currentLine <= fragment.getEndLine(); currentLine++) {
			CloneIntersectionMatrix matrixIntersection = getFragmentByLine(matrixIntersections, currentLine);

			if (ParseFiles.CLONEDIGGER.equals(appName)) {
				matrixIntersection.cloneDigger = true;
			} else if (ParseFiles.CPD.equals(appName)) {
				matrixIntersection.cpd = true;
			} else if (ParseFiles.SIMIAN.equals(appName)) {
				matrixIntersection.simian = true;
			}

			matrix.put(fragment.getPath(), matrixIntersections);
		}

	}

	public CloneIntersectionMatrix getFragmentByLine(Map<Integer, CloneIntersectionMatrix> matrixIntersections, Integer line) {
		CloneIntersectionMatrix entry = matrixIntersections.get(line);
		if (entry == null) {
			entry = new CloneIntersectionMatrix();
			matrixIntersections.put(line, entry);
		}
		return entry;
	}

	public Map<String, Map<Integer, CloneIntersectionMatrix>> getMatrix() {
		return matrix;
	}

}
