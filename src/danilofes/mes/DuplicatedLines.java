package danilofes.mes;

import java.util.HashSet;
import java.util.Set;


public class DuplicatedLines {

	private Set<Integer> lines = new HashSet<Integer>();
	
	public void markRange(int start, int end) {
		for (int i = start; i <= end; i++) {
			this.lines.add(i);
		}
	}

	public int count() {
		return lines.size();
	}
}
