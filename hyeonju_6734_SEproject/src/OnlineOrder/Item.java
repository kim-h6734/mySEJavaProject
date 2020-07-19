package OnlineOrder;

public enum Item {
	SABLEFISH("Sablefish", 10, 250),
	DUCKBREAST("Duck breast", 0, 340),
	SALMON("Salmon sushi roll", 5, 250),
	VEAL("Veal", 5, 170),
	LOBSTER("Lobster tail", 15, 170),
	LAMB("Lamb chop", 12, 300),
	HAMACHI("Hamachi sashimi", 0, 230),
	SCALLOP("Scallop roll", 15, 230),
	OCTOPUS("Octopus cake", 7, 150),
	LINGCOD("Lingcod steak", 10, 100),
	MUSSEL("Mussel", 0, 170);
	
	
	private String itemName;
	private double itemPrice;
	private String itemDesc;
	private double discRate;
	private int calories;

	
	private Item(String itemDesc, double discRate, int calories) {
		this.itemDesc = itemDesc;
		this.discRate = discRate;
		this.calories = calories;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public double getDiscRate() {
		return discRate/100.0;
	}
	public int getCalories() {
		return calories;
	}
	public void addItem()	{
		// to be continued
	}
	public void deleteItem()	{
		// to be continued
	}
	
}
