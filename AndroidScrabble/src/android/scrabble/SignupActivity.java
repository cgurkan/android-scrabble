package android.scrabble;

import java.util.Observable;
import java.util.Observer;

import util.SendObject;

import model.SignupModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity implements Observer, OnClickListener{
	
	private SignupModel model = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        String userName = getIntent().getStringExtra("USERNAME");

        model = new SignupModel();
        model.addObserver(this);
        
        TextView nameTaken = (TextView)(findViewById(R.id.nameTaken));
    	nameTaken.setText("The username "+userName+" does not exist!");
    	
    	Button submit = (Button)(findViewById(R.id.submit));
    	submit.setOnClickListener(this);
    }

	@Override
	public void onClick(View v){
        EditText input = (EditText)(findViewById(R.id.username));
        
        model.sendLoginRequest(input.getText().toString());
	}

	@Override
	public void update(Observable observable, Object data) {
        EditText input = (EditText)(findViewById(R.id.username));
        String username = input.getText().toString();
		SendObject object = (SendObject)(data);
		if(object.getObject().toString().equalsIgnoreCase("You are now signed up, welcome "+username)){
			AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
			builder.setMessage(object.getObject().toString());
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
}