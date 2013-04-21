package project.prototype.ipmedt4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
 * @author Jim Schoorl
 *
 */
public class AsyncTaskPull extends Activity {

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	
	TextView txt;
	TextView txt2;
	Button btn_start;
	ProgressBar progressBar;
	TextView txt_percentage;
	
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);
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
			String URL = "http://10.0.2.2/android_connect/test.php";
			System.out.println("Query");


			//http post
			try{
				HttpClient httpclient = new DefaultHttpClient();
				System.out.println("1");				
				HttpPost httppost = new HttpPost(URL);
				System.out.println("2");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				System.out.println("3");
				HttpResponse response = httpclient.execute(httppost);
				System.out.println("4");
				HttpEntity entity = response.getEntity();
				System.out.println("5");
				is = entity.getContent();
				System.out.println("Http setup");


			}catch(Exception e){
				Log.e("log_tag", "Error in http connection "+e.toString());
			}

			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
				System.out.println("BufferedRead");
			}catch(Exception e){
				Log.e("log_tag", "Error converting result "+e.toString());
			}
			//parse json data
			String returnString = null;
			try{
				//JSONArray jArray = new JSONArray(result);
				JSONObject json_data = new JSONObject(result);
				JSONArray nameArray = json_data.names();
				JSONArray valArray = json_data.toJSONArray(nameArray);
				// Loop om alles in de collumns te vinden.
				/*
				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					Log.i("log_tag"," Ville_ID: "+json_data.getString("Ville_ID")  );
					
					//Get an output to the screen
					returnString += "\n\t" + jArray.getJSONObject(i);	
				}
				*/
			
			}
			
			catch(JSONException e)
			{
				Log.e("log_tag", "Error parsing data "+e.toString());
				System.out.println("JSONParse" + e.toString());
			}
			
			return returnString; 

		}  

	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	
			Toast.makeText(AsyncTaskPull.this,
					"Invoke onPostExecute()", Toast.LENGTH_SHORT).show();
	
			txt.setText(result); 

			btn_start.setEnabled(true);
			System.out.println("TA-DAH!");
		}
	}
}