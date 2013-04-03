package hsl.imtpmd.client;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{
	private ServerCommunicator serverCommunicator;
	private String receivedServerMessage;
	
	private EditText editTextMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) this.findViewById( R.id.buttonsend );
		button.setOnClickListener(this);
		System.out.println("dit doet het in ieder geval");
	}
	
	//onClick methode die door de button wordt aangeroepen
	@Override
	public void onClick( View view )
	{
		//haal gegevens op uit de UI
		editTextMessage = (EditText) this.findViewById( R.id.edittextmessage );
		EditText editTextIp = (EditText) this.findViewById( R.id.edittextip );
		EditText editTextPoort = (EditText) this.findViewById( R.id.edittextport );
		String message = editTextMessage.getText().toString();
		String serverPortS = editTextPoort.getText().toString();
		String serverIp = editTextIp.getText().toString();

		//Integer.parseInt(editTextPoort.getText().toString())
		int serverPort = Integer.parseInt(serverPortS);
		//EditText editTextPort = editTextPoort;
		
		//maak een nieuwe verbinding met de server
		this.serverCommunicator = new ServerCommunicator( this, serverIp, serverPort, message );
		//this.serverCommunicator.
		//bij ontvangen van een bericht wordt de methode setReceivedServerMessage en run aangeroepen
		//om het bericht van de server binnen te halen en op het scherm te tonen
		System.out.println("check");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setOntvangenBericht(String message)
	{
		editTextMessage.setText(message);
	}

}
