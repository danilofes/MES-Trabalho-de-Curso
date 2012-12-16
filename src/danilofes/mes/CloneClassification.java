package danilofes.mes;

import java.util.ArrayList;
import java.util.List;


public class CloneClassification {

	enum Criticity {FALSE_POSITIVE, HARMLESS_CLONE, HARMFUL_CLONE}
	
	private static List<CloneClassification> items = new ArrayList<CloneClassification>();
	
	long id;
	Criticity criticity;
	String type = "";
	String subtype = "";
	
	boolean simian = false;
	boolean cpd = false;
	boolean cloneDigger = false;

	private DuplicatedLines f1Lines = new DuplicatedLines();
	
	public CloneClassification(Criticity criticity, long id) {
		this.id = id;
		this.criticity = criticity;
	}

	public static CloneClassification falsePositive(long id) {
		CloneClassification cloneClassification = new CloneClassification(Criticity.FALSE_POSITIVE, id);
		items.add(cloneClassification);
		return cloneClassification;
	}
	public static CloneClassification codeClone(long id) {
		CloneClassification cloneClassification = new CloneClassification(Criticity.HARMLESS_CLONE, id);
		items.add(cloneClassification);
		return cloneClassification;
	}
	public static CloneClassification harmfulClone(long id) {
		CloneClassification cloneClassification = new CloneClassification(Criticity.HARMFUL_CLONE, id);
		items.add(cloneClassification);
		return cloneClassification;
	}
	
	public CloneClassification cp(String line, String tool, long id) {
		String[] linesStr = line.split("[\\-> ]+");
		if (linesStr.length != 4) {
			throw new RuntimeException("Formato inválido: " + line);
		}
		this.f1Lines.markRange(Integer.parseInt(linesStr[0]), Integer.parseInt(linesStr[1]));
		
		if (tool.equals("cpd")) {
			this.cpd = true;
		} else if (tool.equals("simian")) {
			this.simian = true;
		} else if (tool.equals("clonedigger")) {
			this.cloneDigger = true;
		} else {
			throw new RuntimeException("Ferramenta inválida: " + tool);
		}
		
		return this;
	}
	public CloneClassification forking() {
		this.type = "Forking";
		return this;
	}

	public CloneClassification templating() {
		this.type = "Templating";
		return this;
	}
	public CloneClassification boilerPlating() {
		this.subtype = "Boiler-plating Due to Language Inexpressiveness";
		return this;
	}
	public CloneClassification apiProtocol() {
		this.subtype = "API/Library Protocols";
		return this;
	}
	public CloneClassification languageIdioms() {
		this.subtype = "General Language or Algorithmic Idioms";
		return this;
	}
	public CloneClassification parameterized() {
		this.subtype = "Parameterized Code";
		return this;
	}

	public CloneClassification customization() {
		this.type = "Customization";
		return this;
	}
	public CloneClassification bugWorkaround() {
		this.subtype = "Bug Workarounds";
		return this;
	}
	public CloneClassification specialize() {
		this.subtype = "Replicate and Specialize";
		return this;
	}
	
	public CloneClassification exactMatch() {
		this.type = "Exact Matches";
		return this;
	}
	public CloneClassification crossCutting() {
		this.subtype = "Cross-cutting Concerns";
		return this;
	}
	public CloneClassification snippet() {
		this.subtype = "Verbatim Snippets";
		return this;
	}

	public static void printResults() {
		System.out.println(items.size() + " resultados:");
		for (CloneClassification cc : items) {
			System.out.println("insert into dcc890.CloneClassification(size, nature, cat, subcat, simian, cpd, cdigger) values ");
			System.out.println(cc);
			System.out.println(";");
		}
		System.out.println("===========================");
	}
	
	@Override
	public String toString() {
		return String.format(
				"(%d, \'%s\', \'%s\', \'%s\', %d, %d, %d)",
				this.clonedLines(),
				this.criticity.toString(),
				this.type,
				this.subtype,
				this.simian ? 1 : 0,
				this.cpd ? 1 : 0,
				this.cloneDigger ? 1 : 0
				);
	}
	
	public int clonedLines() {
		return this.f1Lines.count();
	}
	
	enum ForkingType {
		HARDWARE, PLATFORM, EXPERIMENTAL
	}
	
}

