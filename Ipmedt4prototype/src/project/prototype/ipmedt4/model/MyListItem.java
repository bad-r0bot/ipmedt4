package project.prototype.ipmedt4.model;

//deze klasse houdt de data vast die in de view (listview_item.xml) gepresenteerd wordt
//we kunnen nieuwe items maken en in een arraylist aan de listview adapter meegeven
//de data wordt door de listview adapter veranderd als de gebruiker via de listview iets veranderd
//in dit geval: checkbox aan/uit zetten
public class MyListItem
{
	//deze
	private String naam;
	private Boolean checked;
	
	public MyListItem( String naam )
	{
		this.naam = naam;
		this.checked = false;
	}
	
	public void setText( String text )
	{
		this.naam = text;
	}
	
	public String getText()
	{
		return(naam);
	}
	
	
	public void setChecked( boolean checked )
	{
		this.checked = checked;
	}
	
	public boolean getChecked()
	{
		return(checked);
	}
}
