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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import android.os.SystemClock;

/**
 * 
 * @author Jim Schoorl, Frans van Nijnanten
 *
 */
public class Item extends Activity {

	
	ArrayList<JSONObject> arrayValue = new ArrayList<JSONObject>();
	TextView txt;
	TextView txt2;
	Button btn_start;
	ProgressBar progressBar;
	TextView txt_percentage;
	String[] attID = null;
	String[] attDesc = null;
	String[] attNaam = null;
	
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		txt = (TextView) findViewById(R.id.textView1);
		
		txt2 = (TextView) findViewById(R.id.TextView2);  

		btn_start = (Button) findViewById(R.id.button2);

		btn_start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				btn_start.setEnabled(false);
				new ShowDialogAsyncTask().execute();
			}
		});
	}

	private class ShowDialogAsyncTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute() 
		{
			// update the UI immediately after the task is executed
			super.onPreExecute();

			Toast.makeText(Item.this,
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
			String URL = "http://10.0.2.2:8080/android_connect/test.php";


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
	
			Toast.makeText(Item.this,"Invoke onPostExecute()", Toast.LENGTH_SHORT).show();
			
			txt.setText(attNaam[0]);
			txt2.setText(attDesc[0]);
			
			btn_start.setEnabled(true);
		}
	}
}