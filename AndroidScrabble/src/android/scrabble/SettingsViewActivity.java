package android.scrabble;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsViewActivity extends Activity  implements OnItemSelectedListener{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);
        

        Spinner languages = (Spinner)(findViewById(R.id.lang));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.langs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languages.setAdapter(adapter);
        languages.setOnItemSelectedListener(this);
    }
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String item = (String) parent.getItemAtPosition(pos);
		
		Locale locale = null;
		if(item.equalsIgnoreCase("English")){
			locale = Locale.ENGLISH;
		}
		else if(item.equalsIgnoreCase("Deutsch")){
			locale = Locale.GERMAN;
		}
		
		if(locale != null){
			Locale.setDefault(locale);
			Configuration config = getBaseContext().getResources().getConfiguration();
			if(!config.locale.equals(locale)){
				config.locale = locale;
				getBaseContext().getResources().updateConfiguration(config, null);
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Locale locale = Locale.getDefault();
		Locale.setDefault(locale);
		Configuration config = getBaseContext().getResources().getConfiguration();
		if(!config.locale.equals(locale)){
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config, null);
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
}