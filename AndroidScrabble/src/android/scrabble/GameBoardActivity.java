package android.scrabble;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class GameBoardActivity extends Activity implements OnClickListener{

	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        /*
	        TableLayout masterLayout = new TableLayout(this);
	                
	        //Adding the board to the main layout
	        masterLayout.addView(createBoardGrid());
	        masterLayout.addView(createCharGrid());
	        masterLayout.addView(createPlayerGrid());
	        masterLayout.addView(createButtonGrid());*/
	        
	        super.setContentView(R.layout.gameboard2);
	    }
	   
	    public TableLayout createBoardGrid() {
	        TableLayout boardGrid = new TableLayout(this);
	        boardGrid.setLayoutParams(new TableLayout.LayoutParams(15, 15));
	        
	        boardGrid.setPadding(1, 1, 1, 1);
	        
	        for (int i = 0; i < 15; i++) {
	        	TableRow tr = new TableRow(this);
	        	for (int n = 0; n <15; n++) {
	        		 Button b = new Button(this);
	                 b.setText(""+i+n);
	                 b.setTextSize(10.0f);
	                 b.setTextColor(Color.rgb(100, 200, 200));
	                 b.setOnClickListener(this);
	                 tr.addView(b, 30, 30);
	        	}
	        	boardGrid.addView(tr);
	        }
	    	return boardGrid;
	    }
	    
	    public TableLayout createCharGrid() {
	    	TableLayout charGrid = new TableLayout(this);
	        charGrid.setLayoutParams(new TableLayout.LayoutParams(1,8));
		        
	        charGrid.setPadding(1,1,1,1);

	        for (int i = 0; i < 1; i++) {
	        	TableRow tr = new TableRow(this);
	        	for(int n = 0; i < 8; i++) {
	        		Button b = new Button (this);
	        		b.setText(""+i+n);
	        		b.setTextSize(10.0f);
	        		b.setTextColor(Color.rgb( 100, 200, 200));
	        		b.setOnClickListener(this);
	        		tr.addView(b, 30,30);
	        	}
	        charGrid.addView(tr);
	        }
	        return charGrid;
	    }
	    
	    public TableLayout createPlayerGrid() {
	    	TableLayout playerGrid = new TableLayout(this);
	    	playerGrid.setLayoutParams(new TableLayout.LayoutParams(2, 2));
	    	
	    	playerGrid.setPadding(1,1,1,1);
	    	
	    	for (int i = 0; i < 2; i++) {
	        	TableRow tr = new TableRow(this);
	        	for(int n = 0; i < 2; i++) {
	        		TextView tw = new TextView(this);
	        		tw.setText(""+i+n);
	        		tw.setTextSize(10.0f);
	        		tw.setTextColor(Color.rgb( 100, 200, 200));
	        		tw.setOnClickListener(this);
	        		tr.addView(tw, 30,30);
	        	}
	        playerGrid.addView(tr);
	        }
	        return playerGrid;
	    }
	    
	    public TableLayout createButtonGrid() {
	    	TableLayout buttonGrid = new TableLayout(this);
	        buttonGrid.setLayoutParams(new TableLayout.LayoutParams(1,8));
		        
	        buttonGrid.setPadding(1,1,1,1);

	        for (int i = 0; i < 1; i++) {
	        	TableRow tr = new TableRow(this);
	        	for(int n = 0; i < 3; i++) {
	        		Button b = new Button (this);
	        		b.setText(""+i+n);
	        		b.setTextSize(10.0f);
	        		b.setTextColor(Color.rgb( 100, 200, 200));
	        		b.setOnClickListener(this);
	        		tr.addView(b, 30,30);
	        	}
	        buttonGrid.addView(tr);
	        }
	        return buttonGrid;
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
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	}