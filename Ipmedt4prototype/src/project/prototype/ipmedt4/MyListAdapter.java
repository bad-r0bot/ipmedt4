package project.prototype.ipmedt4;

import project.prototype.ipmedt4.model.MyListItem;

import java.util.ArrayList;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter implements OnCheckedChangeListener
{
	//dit is de lijst met alle items
	//de lijst wordt gevuld in een andere klasse, we gebruiken de lijst hier alleen om uit te lezen
	private ArrayList<MyListItem> itemArrayList;
	
	
	//de constructor van de adapter
	public MyListAdapter( ArrayList<MyListItem> itemArrayList )
	{
		//we gebruiken een bestaande arraylist om de data uit te halen
		this.itemArrayList = itemArrayList;
	}

	
	@Override
	public int getCount()
	{
		//geef het aantal objecten terug dat in de itemArrayList staan
		return(itemArrayList.size());
	}

	@Override
	public Object getItem(int position)
	{
		//geef het item terug dat op de gevraagde positie staat in de itemArrayList
		//in dit geval is dat het object dat op de positie "position" staat in de arraylist
		return(itemArrayList.get(position));
	}

	@Override
	public long getItemId(int position)
	{
		//geef de index terug van het element die op de gevraagde positie staat in de itemArrayList
		//in dit geval is de positie "position" gelijk aan de index in de arraylist
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//de convertView is de view die al een keer eerder getoond is door de ListView
		//deze kunnen we hergebruiken als hij al bestaat, maar
		//kan ook null zijn als de lijst voor het eerst getoond wordt
		//dit controleren we hieronder in het if statement
		if( convertView == null )
		{
			//bij een niet-bestaand listview item wordt een nieuw item gemaakt 
			//gebruik als Context object voor de inflater parent.getContext()
			LayoutInflater inflater = (LayoutInflater) parent.getContext()
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
			convertView = inflater.inflate( R.layout.listview_item, parent, false );
			
			//we slaan de positie op in de convertView's tag om later op te halen en input uit te lezen
			CheckBox itemCheckBox = (CheckBox) convertView.findViewById( R.id.itemcheckbox );
			itemCheckBox.setTag( R.string.itemtag_position, position );
			
			//tot slot van rekening willen we opvangen wanneer de checkbox wordt ingedrukt.
			//We hangen een checkedChangeListener aan de checkbox
			itemCheckBox.setOnCheckedChangeListener( this );
		}
		
		//haal het item op uit de lijst dat op de meegegeven positie getoond moet worden
		MyListItem item = itemArrayList.get(position);
		
		//vind het TextView en de CheckBox interface objecten waar de naam en de check van het item in moet komen
		TextView itemNaam = (TextView) convertView.findViewById( R.id.itemnaam );
		CheckBox itemCheckBox = (CheckBox) convertView.findViewById( R.id.itemcheckbox );
		
		//vul het TextView in met de naam van het item
		itemNaam.setText(item.getText());
		
		
		//update de huidige positie van de checkbox in de zichtbare lijst
		itemCheckBox.setTag( R.string.itemtag_position, position );

		//stel de checkbox in op checked of unchecked
		itemCheckBox.setChecked( item.getChecked());
		
		return convertView;
	}


	//Als een item in de ListView is aangeklikt willen we informatie updaten naar het bijbehorende model
	//In dit geval: Checkbox status (aan of uit) updaten naar de MyListItem.checked eigenschap die er bij hoort
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		//we halen het position object van de checkbox in de lijst weer op om bijbehorende item van de itemArrayList te halen
		Integer position = (Integer) buttonView.getTag( R.string.itemtag_position );
		MyListItem item = this.itemArrayList.get( position.intValue() );
		
		item.setChecked( buttonView.isChecked() );
	}
	

}
