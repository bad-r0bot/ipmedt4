package project.prototype.ipmedt4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Categorie1 extends Activity
{	
	Button settings;
	Button search;
	Button terug;
	Button item1;
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
        case R.id.info:
            openContact(settings);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    

	private void openContact(View view) {

               Intent myIntent = new Intent(view.getContext(), Info.class);
               startActivityForResult(myIntent, 0);
           }


	//maak een listener die buttons de mogelijkheid geeft om ingedrukt te worden
	public void addListenerOnButton() {
		
		settings = (Button) findViewById(R.id.settings);

		settings.setOnClickListener(new OnClickListener() {
			//koppel de button aan een nieuw xml scherm wat opent
			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), Settings.class);
				startActivityForResult(myIntent, 0);
			}

		});

		search = (Button) findViewById(R.id.search);

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), Search.class);
				startActivityForResult(myIntent, 0);
			}
		});
	
		terug = (Button) findViewById(R.id.terug);

		terug.setOnClickListener(new OnClickListener() {

			@Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
 
		});
		
		item1 = (Button) findViewById(R.id.settings);

		item1.setOnClickListener(new OnClickListener() {
			//koppel de button aan een nieuw xml scherm wat opent
			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), AsyncTaskPull.class);
				startActivityForResult(myIntent, 0);
			}

		});
	}
	
    
}
