package project.prototype.ipmedt4;

import project.prototype.ipmedt4.model.MyListItem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity
{	
	//deze methode overriden we zodat we commando's kunnen uitvoeren zodra de applicatie is opgestart
	//we voeren bijna nooit commando's uit in de constructor van een userinterface object, maar in de onCreate(...)
	//de onCreate van deze Activity klasse wordt door Android aangeroepen als deze eenmaal goed en wel draait
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //we willen een lijst zien met data uit een reeks van MyListView objecten
        //we halen eerst het ListView object op dat we in activity_main.xml hebben gedefinieerd m.b.v. findViewById( R.id.object_id )
        ListView listView = (ListView) this.findViewById( R.id.listview );
        
        
        //we maken een nieuwe arraylist waar we al onze data in zetten
        ArrayList<MyListItem> itemArrayList = new ArrayList<MyListItem>();
        
        //en we voegen wat data aan de arraylist
       	itemArrayList.add( new MyListItem( "Categorie 1" ) );
       	itemArrayList.add( new MyListItem( "Categorie 2" ) );
       	itemArrayList.add( new MyListItem( "Categorie 3" ) );
       	itemArrayList.add( new MyListItem( "Categorie 4" ) );
       	itemArrayList.add( new MyListItem( "Categorie 5" ) );
       	itemArrayList.add( new MyListItem( "Categorie 6" ) );
       	itemArrayList.add( new MyListItem( "Categorie 7" ) );
       	itemArrayList.add( new MyListItem( "Categorie 8" ) );
       	itemArrayList.add( new MyListItem( "Categorie 9" ) );
        
        
        //we maken tot slot een adapter aan die de data (de arraylist) en de lijst (de listview) aan elkaar koppelt
        //eerst een nieuwe adapter maken waar we de data (arraylist) aan meegeven
		MyListAdapter arrayAdapter = new MyListAdapter( itemArrayList );
		
		//dan de adapter aan de lijst koppelen
		listView.setAdapter( arrayAdapter );
		
		
		//op deze manier hebben we een complete scheiding tussen businesslaag en presentatielaag
		//de adapter is de mediator tussen de twee lagen, alle communicatie van en naar beide lagen verloopt via de adapter
    }

    
    //deze methode overriden we en vullen we zelf in, zodat we een werkende menubalk hebben
    //de code wordt automatisch gegenereerd bij het maken van een nieuw android project
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
