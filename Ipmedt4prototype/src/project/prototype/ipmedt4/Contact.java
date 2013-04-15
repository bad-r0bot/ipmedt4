package project.prototype.ipmedt4;

import project.prototype.ipmedt4.model.MyListItem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Contact extends Activity
{	
	Button button2;
	//deze methode overriden we zodat we commando's kunnen uitvoeren zodra de applicatie is opgestart
	//we voeren bijna nooit commando's uit in de constructor van een userinterface object, maar in de onCreate(...)
	//de onCreate van deze Activity klasse wordt door Android aangeroepen als deze eenmaal goed en wel draait
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        
		addListenerOnButton();

    }

    
    //deze methode overriden we en vullen we zelf in, zodat we een werkende menubalk hebben
    //de code wordt automatisch gegenereerd bij het maken van een nieuw android project
    
	public void addListenerOnButton() {
		 
		button2 = (Button) findViewById(R.id.widget40);
 
		button2.setOnClickListener(new OnClickListener() {
 
			@Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
 
		});
 
	}
    
}
