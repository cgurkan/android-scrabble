package android.scrabble;

import java.util.Observable;
import java.util.Observer;

import model.LoginModel;

import util.SendObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements Observer, OnClickListener{
    
	private LoginModel model;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        model = new LoginModel();
        model.addObserver(this);
        
        Button submit = (Button)(findViewById(R.id.submit));
        submit.setOnClickListener(this);
    }
    
	@Override
	public void update(Observable obs, Object obj) {
		SendObject so = (SendObject)(obj);
		
        EditText input = (EditText)(findViewById(R.id.username));
        String uName = input.getText().toString();
        if(so.getObject().toString().equalsIgnoreCase("The requested username "+uName+" does not exist. Please sign up!")){

        	Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        	intent.putExtra("USERNAME", uName);
        	startActivity(intent);
        }
        finish();
	}

	@Override
	public void onClick(View v){
        EditText input = (EditText)(findViewById(R.id.username));
        
        model.sendLoginRequest(input.getText().toString());
	}
}