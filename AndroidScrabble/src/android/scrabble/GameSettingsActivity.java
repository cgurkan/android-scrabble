package android.scrabble;

import util.ResponseObject;

import model.GameSettingsModel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GameSettingsActivity extends Activity implements OnClickListener{
	
	private GameSettingsModel model = null;
	private String opponent = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings);
        
        model = new GameSettingsModel(getApplicationContext());
        initDictionaries();
        
        Button search = ((Button)(findViewById(R.id.settings_search_submit)));
        search.setOnClickListener(this);
    }
    
    public void initDictionaries(){
    	ResponseObject dicObj = model.getDictionaries();
    	String[] dics = ((String[])(dicObj.getObject()));
    	
    	Spinner spinner = ((Spinner)findViewById(R.id.settings_dictionary));
    	
    	ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dics);
    	spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(spinnerArrayAdapter);
    }
    
    public void isOnline(String username){
    	ResponseObject pplOnline = model.getPeopleOnline();
    	String[] ppl = ((String[])pplOnline.getObject());
    	
    	boolean isOnline = false;
    	for(String s : ppl){
    		if(s.equalsIgnoreCase(username)){
    			isOnline = true;
    			break;
    		}
    	}
    	
    	TextView result = ((TextView)findViewById(R.id.settings_search_result));
    	if(isOnline){
    		result.setTextColor(Color.GREEN);
    		result.setText(username+" är online");
    		opponent = username;
    	}
    	else{
    		result.setTextColor(Color.RED);
    		result.setText(username+" är inte online");
    	}
    }
    
	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.settings_search_submit:
				TextView v = ((TextView)findViewById(R.id.settings_search_field));
				isOnline(v.getText().toString());
				break;
			case R.id.settings_start_game:
				break;
		}
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
	
	public void debug(String s){
    	Context context = getBaseContext();
    	CharSequence text = s;
    	int duration = Toast.LENGTH_LONG;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
}
