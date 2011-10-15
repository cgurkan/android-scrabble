package android.scrabble;

import util.UserData;

import model.LogoutModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class LogoutActivity extends Activity{
    
	private LogoutModel model;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        
        model = new LogoutModel(getBaseContext());
        
        String username = getIntent().getStringExtra("USERNAME");
        
        if(username == null){
        	model.dispose();
        	finish();
        }
        else{
        	String res = model.sendLogoutRequest(username);
    		UserData.username = "";
    		showLoggedOutDialog(res);
    		model.dispose();
        }
    }

	public void showLoggedOutDialog(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(LogoutActivity.this);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
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