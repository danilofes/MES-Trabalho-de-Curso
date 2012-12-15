package danilofes.mes.db.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import danilofes.mes.ParseFiles;
import danilofes.mes.db.entity.generic.GenericFragment;

public class CloneMatrixWrapper {
	private Map<String, List<CloneIntersectionMatrix>> matrix = new HashMap<String, List<CloneIntersectionMatrix>>();

	public void addFragmentList(List<GenericFragment> fragments, String appName) {

		for (GenericFragment fragment : fragments) {
			List<CloneIntersectionMatrix> matrixIntersections = matrix.get(fragment.getPath());

			if (matrixIntersections == null) {
				matrixIntersections = new ArrayList<CloneIntersectionMatrix>();
			}

			CloneIntersectionMatrix matrixIntesection = getFragmentByLine(matrixIntersections, fragment.getLine());
			if (matrixIntesection == null) {
				matrixIntesection = new CloneIntersectionMatrix();
				matrixIntesection.line = fragment.getLine();
			}

			if (ParseFiles.CLONEDIGGER.equals(appName)) {
				matrixIntesection.cloneDigger = true;
			} else if (ParseFiles.CPD.equals(appName)) {
				matrixIntesection.cpd = true;
			} else if (ParseFiles.SIMIAN.equals(appName)) {
				matrixIntesection.simian = true;
			}

			matrixIntersections.add(matrixIntesection);
			matrix.put(fragment.getPath(), matrixIntersections);
		}

	}

	private CloneIntersectionMatrix getFragmentByLine(List<CloneIntersectionMatrix> matrixIntersections, Integer line) {
		for (CloneIntersectionMatrix matrix : matrixIntersections) {
			if (matrix.line == line) {
				return matrix;
			}
		}

		return null;
	}

	public Map<String, List<CloneIntersectionMatrix>> getMatrix() {
		return matrix;
	}

}