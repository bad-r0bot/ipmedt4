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

public class MainActivity extends Activity
{
	Button settings;
	Button search;
	Button contact;

	// laad de XML in
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// koppel de xml aan de java
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Intent myIntent = new Intent(view.getContext(), SearchMenu.class);
		//startActivityForResult(myIntent, 0);

		addListenerOnButton();

		// laad het listview object
		ListView listView = (ListView) this.findViewById(R.id.listview);

		// maak een arraylist, deze vullen we meteen met data die uiteindelijk
		// in de list moet
		ArrayList<MyListItem> itemArrayList = new ArrayList<MyListItem>();

		// maak namen bij de arraylist
		itemArrayList.add(new MyListItem("Categorie 1"));
		itemArrayList.add(new MyListItem("Categorie 2"));
		itemArrayList.add(new MyListItem("Categorie 3"));
		itemArrayList.add(new MyListItem("Categorie 4"));
		itemArrayList.add(new MyListItem("Categorie 5"));
		itemArrayList.add(new MyListItem("Categorie 6"));
		itemArrayList.add(new MyListItem("Categorie 7"));
		itemArrayList.add(new MyListItem("Categorie 8"));
		itemArrayList.add(new MyListItem("Categorie 9"));
		itemArrayList.add(new MyListItem("Categorie 10"));
		itemArrayList.add(new MyListItem("Categorie 11"));
		itemArrayList.add(new MyListItem("Categorie 12"));
		itemArrayList.add(new MyListItem("Categorie 13"));
		itemArrayList.add(new MyListItem("Categorie 14"));
		itemArrayList.add(new MyListItem("Categorie 15"));

		// creeer een nieuwe listadapter
		MyListAdapter arrayAdapter = new MyListAdapter(itemArrayList);

		// koppel de adapter aan de eerder gemaakte lijst
		listView.setAdapter(arrayAdapter);

	}

	// maak een menubalk
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// vul de menubalk in
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.contact:
			openContact(search);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openContact(View view)
	{

		Intent myIntent = new Intent(view.getContext(), Contact.class);
		startActivityForResult(myIntent, 0);
	}

	// maak een listener die buttons de mogelijkheid geeft om ingedrukt te
	// worden
	public void addListenerOnButton()
	{
		// koppel de button code aan een widget en voeg een onclicklistener toe
		settings = (Button) findViewById(R.id.widget35);

		settings.setOnClickListener(new OnClickListener()
		{
			// koppel de button aan een nieuw xml scherm wat opent
			@Override
			public void onClick(View view)
			{

				Intent myIntent = new Intent(view.getContext(), Settings.class);
				startActivityForResult(myIntent, 0);
			}

		});
		// zelfde als bovenstaande button
		search = (Button) findViewById(R.id.widget36);

		search.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view)
			{

				Intent myIntent = new Intent(view.getContext(), SearchMenu.class);
				startActivityForResult(myIntent, 0);
			}

		});

	}

}
