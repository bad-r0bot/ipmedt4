package project.prototype.ipmedt4;

import project.prototype.ipmedt4.model.MyListItem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Settings extends Activity
{	
	Button button;
	Button button1;
	//laden van XML
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	//het koppelen van de xml aan java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
		addListenerOnButton();

		//laadt het listview object
        ListView listView = (ListView) this.findViewById( R.id.listview );
        
        
        //we maken een nieuwe arraylist        
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
       	itemArrayList.add( new MyListItem( "Categorie 10" ) );
       	itemArrayList.add( new MyListItem( "Categorie 11" ) );
       	itemArrayList.add( new MyListItem( "Categorie 12" ) );
       	itemArrayList.add( new MyListItem( "Categorie 13" ) );
       	itemArrayList.add( new MyListItem( "Categorie 14" ) );
       	itemArrayList.add( new MyListItem( "Categorie 15" ) );
        
        
      //creeër een nieuwe listadapter voor het doorvoeren van de arraylist
		MyListAdapter arrayAdapter = new MyListAdapter( itemArrayList );
		
		//koppel de adapter aan de eerder gemaakte lijst
		listView.setAdapter( arrayAdapter );
		
    }

    
    //maak een menubalk
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //vul de menubalk
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // verwijs de info knop door naar het info scherm.
        switch (item.getItemId()) {
        case R.id.contact:
            openContact(button);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    

	private void openContact(View view) {

               Intent myIntent = new Intent(view.getContext(), Contact.class);
               startActivityForResult(myIntent, 0);
           }
    
	public void addListenerOnButton() {
		 
		button = (Button) findViewById(R.id.terug);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
 
		});
 
	}
    
}
