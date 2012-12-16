package danilofes.mes;


public class CloneClassification {

	private enum Criticity {FALSE_POSITIVE, CLONE, HARMFUL_CLONE}
	
	long id;
	Criticity criticity;
	String type;
	String subtype;
	
	public CloneClassification(Criticity criticity, long id) {
		this.id = id;
		this.criticity = criticity;
	}
	public static CloneClassification falsePositive(long id) {
		return new CloneClassification(Criticity.FALSE_POSITIVE, id);
	}
	public static CloneClassification codeClone(long id) {
		return new CloneClassification(Criticity.CLONE, id);
	}
	public static CloneClassification harmfulClone(long id) {
		return new CloneClassification(Criticity.HARMFUL_CLONE, id);
	}
	public CloneClassification cp(String line, String tool, long id) {
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

	enum ForkingType {
		HARDWARE, PLATFORM, EXPERIMENTAL
	}
}

