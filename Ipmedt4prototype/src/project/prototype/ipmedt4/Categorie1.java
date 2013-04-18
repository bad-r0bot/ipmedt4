package project.prototype.ipmedt4;

import project.prototype.ipmedt4.model.MyListItem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Categorie1 extends Activity
{	
	Button settings;
	Button search;
	Button contact;
	Button categorie1;
	Button terug;
	//laadt de XML in
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	//koppel de xml aan de java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorie1);
        
		addListenerOnButton();

        //laad het listview object
		
    }

    
    //maak een menubalk
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	
        //vul de menubalk in
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // verwijs de info knop door naar het info scherm.
        switch (item.getItemId()) {
        case R.id.contact:
            openContact(search);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    

	private void openContact(View view) {

               Intent myIntent = new Intent(view.getContext(), Contact.class);
               startActivityForResult(myIntent, 0);
           }


	//maak een listener die buttons de mogelijkheid geeft om ingedrukt te worden
	public void addListenerOnButton() {
		 //koppel de button code aan een widget en voeg een onclicklistener toe
		settings = (Button) findViewById(R.id.widget35);
 
		settings.setOnClickListener(new OnClickListener() {
			//koppel de button aan een nieuw xml scherm wat opent
			@Override
            public void onClick(View view) {
 
                Intent myIntent = new Intent(view.getContext(), Settings.class);
                startActivityForResult(myIntent, 0);
            }
 
		});
		 //zelfde als bovenstaande button
		search = (Button) findViewById(R.id.widget36);

		search.setOnClickListener(new OnClickListener() {

			@Override
           public void onClick(View view) {

               Intent myIntent = new Intent(view.getContext(), Search.class);
               startActivityForResult(myIntent, 0);
           }

		});
		categorie1 = (Button) findViewById(R.id.button1);

		categorie1.setOnClickListener(new OnClickListener() {

			@Override
           public void onClick(View view) {

               Intent myIntent = new Intent(view.getContext(), Search.class);
               startActivityForResult(myIntent, 0);
           }

		});
		terug = (Button) findViewById(R.id.button5);

		terug.setOnClickListener(new OnClickListener() {

			@Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
 
		});
	}
	
    
}
