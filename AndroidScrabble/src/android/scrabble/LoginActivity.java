package android.scrabble;

import java.util.Observable;
import java.util.Observer;

import model.LoginModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements Observer, OnClickListener{
    
	private LoginModel model;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        model = new LoginModel(getBaseContext());
        model.addObserver(this);
        
        Button submit = (Button)(findViewById(R.id.submit));
        submit.setOnClickListener(this);
    }
    
	@Override
	public void update(Observable obs, Object obj) {
		int responseCode = ((Integer)(obj)).intValue();
		
        EditText input = (EditText)(findViewById(R.id.username));
        String uName = input.getText().toString();

    	if(responseCode == LoginModel.LOGIN_SIGN_UP){
    		Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
    		intent.putExtra("USERNAME", uName);
    		
    		startActivity(intent);
    		finish();
    	}
    	else if(responseCode == LoginModel.LOGIN_NOT_OK){
    		showMessage("There was an error logging in. If you are logged in on another device, please logout there first.");
    	}
    	else if(responseCode == LoginModel.LOGIN_OK){
    		showMessage("You are now logged in!");
    		finish();
    	}
    	else{
    		// TODO: take care of errors
    	}
	}

	@Override
	public void onClick(View v){
        EditText input = (EditText)(findViewById(R.id.username));
        model.sendLoginRequest(input.getText().toString());
	}
	
	public void showMessage(String s){
    	Context context = getBaseContext();
    	CharSequence text = s;
    	int duration = Toast.LENGTH_LONG;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
}