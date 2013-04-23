package project.prototype.ipmedt4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskPull extends Activity {


	ArrayList<JSONObject> arrayValue = new ArrayList<JSONObject>();
	TextView txt;
	TextView txt2;
	Button btn_start;
	ProgressBar progressBar;
	TextView txt_percentage;
	String[] attID = null;
	String[] attDesc = null;
	String[] attNaam = null;
	Button settings;
	Button search;
	Button terug;

	 int itemX;


	//int item = catItem.item;

	JSONParser jParser = new JSONParser();
	//laadt de XML in
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		System.out.println("wat is Item bij OnCreate: " + itemX);
		//koppel de xml aan de java
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);


		addListenerOnButton();
		txt = (TextView) findViewById(R.id.textView1);

		txt2 = (TextView) findViewById(R.id.textView2);  

		btn_start = (Button) findViewById(R.id.button1);

		search = (Button) findViewById(R.id.search);

		settings = (Button) findViewById(R.id.settings);
		
		this.itemX = getItem();
		System.out.println("wat is Item bij this.itemX= getItem(): " + itemX);
		
		btn_start.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				addListenerOnButton();

				btn_start.setEnabled(false);
				new ShowDialogAsyncTask().execute();
			}
		});
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

	private class ShowDialogAsyncTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute() 
		{
			// update the UI immediately after the task is executed
			super.onPreExecute();

			Toast.makeText(AsyncTaskPull.this,
					"Invoke onPreExecute()", Toast.LENGTH_SHORT).show();

			// Set the text and call the connect function.  
			txt.setText("Connecting...");

		}

		@Override
		protected String doInBackground(Void... params) {

			InputStream is = null;

			String result = "";
			//Welke data wordt doorgestuurd.
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("naam","test"));

			// In test.php goes the SQL query.
			String URL = "http://timvandam.nl/App/test.php";


			//http post
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(URL);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();


			}catch(Exception e){
				Log.e("log_tag", "Error in http connection "+e.toString());
			}

			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

				is.close();
				result=sb.toString();

			}

			catch(Exception e)
			{
				Log.e("log_tag", "Error converting result "+e.toString());
			}

			//parse json data
			String returnString = null;

			try{
				JSONArray jArray = new JSONArray(result);

				attID = new String[jArray.length()];
				attNaam = new String[jArray.length()];
				attDesc = new String[jArray.length()];

				// Loop om alles in de collumns te vinden.
				for(int i=0;i<jArray.length();i++)
				{
					//JSONObject json_data = jArray.getJSONObject(i);

					attID[i] = jArray.getJSONObject(i).getString("Attraction_ID");
					attNaam[i] = jArray.getJSONObject(i).getString("Attraction_name_Dutch");
					attDesc[i] = jArray.getJSONObject(i).getString("Attraction_description_Dutch");

				}
			}

			catch(JSONException e)
			{
				Log.e("log_tag", "Error parsing data "+e.toString());
			}

			return returnString; 
		}  

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			Toast.makeText(AsyncTaskPull.this,"Invoke onPostExecute()", Toast.LENGTH_SHORT).show();


			System.out.println("Async: Ik pak de text: " + itemX);
			txt.setText(attNaam[getItem()]);
			txt2.setText(attDesc[getItem()]);





			btn_start.setEnabled(true);
		}
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
		terug = (Button) findViewById(R.id.terug);

		terug.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});

		
	}
public int getItem() {
	System.out.println("Async: getItem: " + itemX);
	return this.itemX;
}

public void setItem(int itemX) {
	System.out.println("Async: setItem: " + itemX);
	this.itemX = itemX;
}
}

