package danilofes.mes;

import java.util.ArrayList;
import java.util.List;


public class CloneClassification {

	enum Criticity {FALSE_POSITIVE, CLONE, HARMFUL_CLONE}
	
	private static List<CloneClassification> items = new ArrayList<CloneClassification>();
	
	long id;
	Criticity criticity;
	String type = "";
	String subtype = "";
	
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
		CloneClassification cloneClassification = new CloneClassification(Criticity.CLONE, id);
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
		
		// TODO
		return this;
	}
	public CloneClassification forking() {
		this.type = "forking";
		return this;
	}

	public CloneClassification templating() {
		this.type = "templating";
		return this;
	}
	public CloneClassification boilerPlating() {
		this.subtype = "boiler-plating due language inexpressivness";
		return this;
	}
	public CloneClassification apiProtocol() {
		this.subtype = "API library protocols";
		return this;
	}
	public CloneClassification languageIdioms() {
		this.subtype = "general language or algorithmic idioms";
		return this;
	}
	public CloneClassification parameterized() {
		this.subtype = "parameterized code";
		return this;
	}

	public CloneClassification customization() {
		this.type = "customization";
		return this;
	}
	public CloneClassification bugWorkaround() {
		this.subtype = "bug workaround";
		return this;
	}
	public CloneClassification specialize() {
		this.subtype = "replicate and specialize";
		return this;
	}
	
	public CloneClassification exactMatch() {
		this.type = "exact matches";
		return this;
	}
	public CloneClassification crossCutting() {
		this.subtype = "cross-cutting concern";
		return this;
	}
	public CloneClassification snippet() {
		this.subtype = "verbatim snippet";
		return this;
	}

	public static void printResults() {
		System.out.println(items.size() + " resultados:");
		for (CloneClassification cc : items) {
			System.out.println(cc);
		}
		System.out.println("===========================");
	}
	
	@Override
	public String toString() {
		return String.format("%d %s %s %s", this.id, this.criticity.toString(), this.type, this.subtype);
	}
	
	enum ForkingType {
		HARDWARE, PLATFORM, EXPERIMENTAL
	}
}

