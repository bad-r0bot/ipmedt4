package project.prototype.ipmedt4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity
{	
	Button settings;
	Button search;
	Button categorie1;
	Button categorie2;
	Button categorie3;
	Button categorie4;
	Button categorie5;
	Button categorie6;
	//laadt de XML in
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//koppel de xml aan de java
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addListenerOnButton();

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
			openInfo(search);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openInfo(View view) {

		Intent myIntent = new Intent(view.getContext(), Info.class);
		startActivityForResult(myIntent, 0);
	}


	//maak een listener die buttons de mogelijkheid geeft om ingedrukt te worden
	public void addListenerOnButton() {

		//koppel de button code aan een widget en voeg een onclicklistener toe
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

		categorie1 = (Button) findViewById(R.id.categorie1);

		categorie1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), Categorie1.class);
				startActivityForResult(myIntent, 0);
			}
		});

		categorie2 = (Button) findViewById(R.id.categorie2);

		categorie2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), NotFound.class);
				startActivityForResult(myIntent, 0);
			}
		});

		categorie3 = (Button) findViewById(R.id.categorie3);

		categorie3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), NotFound.class);
				startActivityForResult(myIntent, 0);
			}
		});

		categorie4 = (Button) findViewById(R.id.categorie4);

		categorie4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), NotFound.class);
				startActivityForResult(myIntent, 0);
			}
		});

		categorie5 = (Button) findViewById(R.id.categorie5);

		categorie5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), NotFound.class);
				startActivityForResult(myIntent, 0);
			}
		});

		categorie6 = (Button) findViewById(R.id.categorie6);

		categorie6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), NotFound.class);
				startActivityForResult(myIntent, 0);
			}
		});

	}
}
