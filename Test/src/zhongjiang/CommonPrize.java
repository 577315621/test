package zhongjiang;

public enum CommonPrize {

	Snail(1,"001"),
	Bell(2,"002"),
	FortuneCat(3,"003"),
	Button(4,"004"),
	Clover(5,"005"),
	Sandwich(6,"006"),
	ElectricLight(7,"007"),
	Lantern(8,"008"),
	YellowBag(9,"009"),
	WhiteBag(10,"010"),
	GreenScarf(11,"011"),
	WoodScarf(12,"012");

	private int id;
	private String name;
	
	CommonPrize(int id, String name) {
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
