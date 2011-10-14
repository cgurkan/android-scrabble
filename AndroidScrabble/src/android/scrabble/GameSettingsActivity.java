package android.scrabble;

import java.util.Observable;
import java.util.Observer;

import util.ResponseObject;

import model.POnlineModel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GameSettingsActivity extends Activity implements Observer{

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        
        POnlineModel model = new POnlineModel(getApplicationContext());
        model.addObserver(this);
        model.sendOnlineRequest();
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
	public void update(Observable obs, Object obj) {
		ResponseObject o = ((ResponseObject)(obj));
		String[] list = ((String[])(o.getObject()));
		
		String all = "";
		
		for(String s : list){
			all += s+"\n";
		}
		
		/*TextView v = ((TextView)(findViewById(R.id.online)));
		v.setText(all);*/
	}
	
	public void debug(String s){
    	Context context = getBaseContext();
    	CharSequence text = s;
    	int duration = Toast.LENGTH_LONG;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
}
