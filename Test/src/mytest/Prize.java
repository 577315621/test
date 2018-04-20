package mytest;


public enum Prize {
	
	prizeOne("001"),
	prizeTwo("002"),
	prizeThree("003"),
	prizeFour("004"),
	prizeFive("005"),
	prizeSix("006"),
	prizeSeven("007"),
	prizeEight("008"),
	prizeNine("009"),
	prizeTen("010"),
	prizeEleven("011"),
	prizeTwelve("012"),
	
	specialOne("021"),
	specialTwo("022"),
	specialThree("023");
	
	private String name;
	
	Prize(String name) {
		this.name =name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
