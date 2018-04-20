package zhongjiang;

public enum SpecialPrize {

	Frog(13,"021"),
	Partner(14,"022"),
	Pattern(15,"023");

	private int id;
	private String name;
	
	SpecialPrize(int id, String name) {
		this.id =id;
		this.name =name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
