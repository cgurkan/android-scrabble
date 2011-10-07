package android.scrabble;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SignupActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle bundle = getIntent().getExtras();

    	//Next extract the values using the key as
    	String userName = bundle.getString("USERNAME");
        setContentView(R.layout.signup);

        TextView nameTaken = (TextView)(findViewById(R.id.nameTaken));
    	nameTaken.setText("The username "+userName+" does not exist!");        
    }
}