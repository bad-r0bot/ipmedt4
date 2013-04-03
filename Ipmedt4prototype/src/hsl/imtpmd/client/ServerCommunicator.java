package hsl.imtpmd.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

public class ServerCommunicator implements Runnable
{
	private MainActivity activity;
	private Thread thread;
	
	private String message;
	private String ip;
	private int port;
	
	Socket serverSocket = new Socket();
	
	public ServerCommunicator( MainActivity activity, String ip, int port, String message )
	{	
		//we gebruiken de activity om de userinterface te updaten
		this.activity = activity;
		
		//gegevens om naar de server te verbinden en een message te sturen
		this.message = message;
		this.ip = ip;
		this.port = port;
		
		//de nieuwe thread kan tekst verzenden en ontvangen van en naar een server
		this.thread = new Thread("deThread");
		System.out.println("double check");
		this.run();
	}
	

	//dit is een methode die niet op de UI thread wordt aangeroepen, maar door onze eigen nieuwe thread
	//we kunnen dus niet zomaar ontvangen berichten in een userinterface object stoppen m.b.v. view.setText( message )
	//hier gebruiken we de activity voor: activity.runOnUiThread( activity )
	@Override
	public void run()
	{
		try
		{
			serverSocket.connect( new InetSocketAddress( this.ip, this.port ), 4000 );
			
			//verzend een bericht naar de server
			new ServerCommunicatorTask().execute();
			//this.sendMessage(message, serverSocket);
			//this.waitForResponse(serverSocket);

			//gebruik de volgende twee methoden van de activity om informatie naar de UI thread (de activity) te sturen
			//this.activity.setOntvangenBericht( "We hebben het bericht verzonden" );
			//this.activity.runOnUiThread( this.activity );
		}
		catch( UnknownHostException e )
		{Log.d("debug", "can't find host");}
		catch( SocketTimeoutException e )
		{Log.d("debug", "time-out");}
		catch (IOException e)
		{e.printStackTrace();}
		catch( NetworkOnMainThreadException e )
		{Log.d("debug", "Network on main thread(?)");}
		try
		{Thread.sleep( 100 );}
		catch (InterruptedException e)
		{e.printStackTrace();}
	}
	

	//ook deze methoden kunnen niet naar de UI direct communiceren, hou hier rekening mee
	private void sendMessage( String message, Socket serverSocket )
	{
		//OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
		
		OutputStreamWriter outputStreamWriter = null;
		
		try
		{outputStreamWriter = new OutputStreamWriter( serverSocket.getOutputStream() );}
		catch (IOException e2)
		{e2.printStackTrace();}
		
		if( outputStreamWriter != null )
		{
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			PrintWriter writer = new PrintWriter( bufferedWriter, true );
			
			writer.println( "Yep" );
			writer.flush();
		}
	}
	
	//wacht op server bericht (na versturen)
	private void waitForResponse(Socket serverSocket)
	{
		//String serverMessage = null;
		
		//new ServerCommunicatorTask().execute();
		//... wacht op een bericht van de server, return het antwoord

		//return serverMessage;
	}
	
	private class ServerCommunicatorTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... arg0)
		{
			String serverMessage = null;
			BufferedReader bufferedReader = null;
			
			OutputStreamWriter outputStreamWriter = null;
			
			try
			{outputStreamWriter = new OutputStreamWriter( serverSocket.getOutputStream() );}
			catch (IOException e2)
			{e2.printStackTrace();}
			
			if( outputStreamWriter != null )
			{
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				PrintWriter writer = new PrintWriter( bufferedWriter, true );
				
				writer.println( "Yep" );
				writer.flush();
			}
			
			//... wacht op een bericht van de server, return het antwoord
			try
			{
				InputStream inputStream = serverSocket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader( inputStream );
				
				bufferedReader = new BufferedReader( inputStreamReader );
			}
			
			catch (IOException e1)
			{
				e1.printStackTrace();
				InetAddress adress = serverSocket.getInetAddress();
				activity.setOntvangenBericht( "Het werkt niet bij" + adress );
				//server.addMessage( "Can't create inputStreamReader to talk to server " + adress );
			}
			
			if( bufferedReader != null )
			{
				try
				{
					String messageLine = bufferedReader.readLine();
					while( messageLine != null )
					{
						activity.setOntvangenBericht( "Wat doet dit?" );
						//server.addMessage( "Client " + serverSocket.getInetAddress() + " says: > " + messageLine );
						messageLine = bufferedReader.readLine();

						Thread.sleep( 100 );
					}
				}
				
				catch( IOException e )
				{e.printStackTrace();}
				catch (InterruptedException e)
				{e.printStackTrace();}
			}
			
			return serverMessage;
		}

		
	}

}
