package project.prototype.ipmedt4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	String str="new";
    static ResultSet rs;
    static PreparedStatement st;
    static Connection con;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final   TextView tv=(TextView)findViewById(R.id.user);

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://10.0.2.2:444/","root","");
            st=con.prepareStatement("select * from country where id=1");
            rs=st.executeQuery();
             while(rs.next())
             {
             str=rs.getString(2);


             }


            tv.setText(str);
            setContentView(tv);
        }
        catch(Exception e)
        {
            tv.setText(str);
        }
    }
}