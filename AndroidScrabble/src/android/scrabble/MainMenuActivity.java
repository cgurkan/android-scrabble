package android.scrabble;

import util.UserData;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener{
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //UserData.init(getBaseContext().getString(android.scrabble.R.string.serverip));

        //startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
        
        Button settings = (Button)(findViewById(R.id.settingsButton));
        settings.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        debug("resume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

	@Override
	public void onClick(View view){
        startActivity(new Intent(MainMenuActivity.this, SettingsViewActivity.class));
	}
	
	 public void debug(String s){
	    Context context = getApplicationContext();
	    CharSequence text = s;
	    int duration = Toast.LENGTH_SHORT;

	    Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
	 }
}