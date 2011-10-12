package android.scrabble;

import util.UserData;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener{
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //UserData.init(getBaseContext().getString(android.scrabble.R.string.serverip));
        
        Button settings = (Button)(findViewById(R.id.settingsButton));
        settings.setOnClickListener(this);
        
        Button help = (Button)(findViewById(R.id.helpButton));
        help.setOnClickListener(this);
        
        Button help = (Button)(findViewById(R.id.helpButton));
        help.setOnClickListener(this);
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
        
        if(UserData.username == ""){
        	startLoginScreen();
        }
        updateLocale();
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
		
		switch(view.getId()){
			case R.id.settingsButton:
				startActivity(new Intent(MainMenuActivity.this, SettingsViewActivity.class));
				break;
			case R.id.helpButton:
				startActivity(new Intent(MainMenuActivity.this, HelpViewActivity.class));
				break;
		}
	}
	
	public void updateLocale(){
		/*
		 * Update all button-texts and so on, with the proper language
		 */
		TextView header = ((TextView)(findViewById(R.id.appName)));
		header.setText(getString(R.string.app_name));
		
		Button newGame = ((Button)(findViewById(R.id.newGameButton)));
		newGame.setText(getString(R.string.new_game));

		Button settings = ((Button)(findViewById(R.id.settingsButton)));
		settings.setText(getString(R.string.settings));

		Button help = ((Button)(findViewById(R.id.helpButton)));
		help.setText(getString(R.string.help));

		Button about = ((Button)(findViewById(R.id.aboutButton)));
		about.setText(getString(R.string.about));
	}
	
	public void startLoginScreen(){
		startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
	}
	
	 public void debug(String s){
	    Context context = getBaseContext();
	    CharSequence text = s;
	    int duration = Toast.LENGTH_SHORT;

	    Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
	 }
}