package android.scrabble;

import java.util.Observable;
import java.util.Observer;

import util.UserData;

import model.SignupModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends Activity implements Observer, OnClickListener{
	
	private SignupModel model = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        String username = getIntent().getStringExtra("USERNAME");
        setTakenName(username, "not");
        
        model = new SignupModel(getBaseContext());
        model.addObserver(this);
    	
    	Button submit = (Button)(findViewById(R.id.submit));
    	submit.setOnClickListener(this);
    }

	@Override
	public void onClick(View v){
        EditText input = (EditText)(findViewById(R.id.username));
        
        if(input.getText().toString().trim().equals("") || input.getText().toString().trim().equals(" ")){
        	setTakenName(input.getText().toString(), "already");
        }
        else{
        	model.sendLoginRequest(input.getText().toString());
        }
    }
	
	public void setTakenName(String username, String exist){
        TextView nameTaken = (TextView)(findViewById(R.id.nameTaken));
    	nameTaken.setText("The username "+username+" does "+exist+" exist!");
	}

	@Override
	public void update(Observable observable, Object data){
        int resCode = ((Integer)(data)).intValue();
		
        if(resCode == SignupModel.SIGNUP_NOT_OK){
            EditText input = (EditText)(findViewById(R.id.username));
            setTakenName(input.getText().toString(), "already");
        }
        else if(resCode == SignupModel.SIGNUP_OK){
            EditText input = (EditText)(findViewById(R.id.username));
            String uName = input.getText().toString();
            
        	UserData.username = uName;
    		
        	showSignedUpDialog("You are now signed up! Welcome!");
        }
        else{
        	//TODO: Take care of error
        }
	}
	
	public void showMessage(String s){
    	Context context = getBaseContext();
    	CharSequence text = s;
    	int duration = Toast.LENGTH_LONG;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
	
	public void showSignedUpDialog(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
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