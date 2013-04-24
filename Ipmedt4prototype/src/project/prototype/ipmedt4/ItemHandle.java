package project.prototype.ipmedt4;


// Deze Class behoudt de nummer van de knop/item die je indrukt.
public final class ItemHandle {

	private static int dbItem;
	static Categorie1 catDBitem = new Categorie1();
	
	public static void main(String[] args)
	{
		dbItem = catDBitem.getNummerTerug();
	}
	
	public void setDBitem(int nummer) 
	{
		
		ItemHandle.dbItem = nummer;
		System.out.println("666- set item nummer: " + dbItem);

	}
	
	public int getDBitem()
	{
		System.out.println("777- get item nummer: " + dbItem);
		return dbItem;
		
	}

}
