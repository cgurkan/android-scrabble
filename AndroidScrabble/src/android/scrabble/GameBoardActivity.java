package android.scrabble;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class GameBoardActivity extends Activity implements OnClickListener{
	
	private TextView playerOne, playerTwo, playerOnePoints, playerTwoPoints; 
	private Button swapLetters, playWord, resignGame;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       
	        ScrollView mainScrollLayout = new ScrollView(this);
	        
	        HorizontalScrollView horScrollLayout = new HorizontalScrollView(this);
	        
	        TableLayout masterLayout = new TableLayout(this);
	                
	        //Adding the board to the main layout
	        horScrollLayout.addView(createBoardGrid());
	        horScrollLayout.addView(createCharGrid());
	        horScrollLayout.addView(createPlayerGrid());
	        horScrollLayout.addView(createButtonGrid());
	        
	        mainScrollLayout.addView(horScrollLayout);
	        
	        masterLayout.addView(mainScrollLayout);
	        
	        super.setContentView(masterLayout);
	        
//	        super.setContentView(R.layout.gameboard2);
	    }
	   
	    public TableLayout createBoardGrid() {
	        TableLayout boardGrid = new TableLayout(this);

	        boardGrid.setLayoutParams(new TableLayout.LayoutParams());
	        
	        boardGrid.setPadding(1, 1, 1, 1);
	        
	        int xx;
   		 	int yy;
	        
   		 	for (int i = 0; i < 15; i++) {
	        	TableRow tr = new TableRow(this);
	        	for (int n = 0; n <15; n++) {
	        		 Button b = new Button(this);
	                 
	        		 //These if-statements should be used to give a correct id to each button, does not work now
	        		 if (i < 10)
	        			 yy = 0;
	        		 if (n < 10)
	        			 xx = 0;
	        		 
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
	        charGrid.setLayoutParams(new TableLayout.LayoutParams(30,30));
		        
	        charGrid.setPadding(1,1,1,1);

	        for (int i = 0; i < 1; i++) {
	        	TableRow tr = new TableRow(this);
	        	for(int n = 0; n < 7; n++) {
	        		Button b = new Button (this);
	        		b.setId(n);
	        		b.setText(""+i+n); //Need to set the character here
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
	    	playerGrid.setLayoutParams(new TableLayout.LayoutParams(30, 30));
	    	
	    	playerGrid.setPadding(1,1,1,1);

        	TableRow tr1 = new TableRow(this);
        	TableRow tr2 = new TableRow(this);
        	
        	playerOne.setText("Play1");	//Need to get the playerName from somewhere
    		playerOne.setTextSize(10.0f);
    		playerOne.setTextColor(Color.rgb( 100, 200, 200));
    		tr1.addView(playerOne, 30,30);
    		
    		playerOnePoints.setText("PlayerOnePoints");	//Need to get the playerOnePoints from somewhere
    		playerOnePoints.setTextSize(10.0f);
    		playerOnePoints.setTextColor(Color.rgb( 100, 200, 200));
    		tr1.addView(playerOnePoints, 30,30);
    		
    		playerTwo.setText("Play2");	//Need to get the playerName from somewhere
    		playerTwo.setTextSize(10.0f);
    		playerTwo.setTextColor(Color.rgb( 100, 200, 200));
    		tr2.addView(playerTwo, 30,30);
    		
        	playerTwoPoints.setText("PlayerTwoPoints");	//Need to get the playerTwoPoints from somewhere
    		playerTwoPoints.setTextSize(10.0f);
    		playerTwoPoints.setTextColor(Color.rgb( 100, 200, 200));
    		tr2.addView(playerTwoPoints, 30,30);
    		
    		playerGrid.addView(tr1);
    		playerGrid.addView(tr2);
	    	
    		return playerGrid;
    		/*
    		for (int i = 0; i < 2; i++) {
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
	        */
	    }
	    
	    public TableLayout createButtonGrid() {
	    	TableLayout buttonGrid = new TableLayout(this);
	        buttonGrid.setLayoutParams(new TableLayout.LayoutParams(30,30));
		        
	        buttonGrid.setPadding(1,1,1,1);
	        
	        TableRow tr = new TableRow(this);
	        
    		swapLetters.setText("Swap letters");
    		swapLetters.setTextSize(10.0f);
    		swapLetters.setTextColor(Color.rgb( 100, 200, 200));
    		swapLetters.setOnClickListener(this);
    		tr.addView(swapLetters, 30,30);
    		
    		playWord.setText("Play word");
    		playWord.setTextSize(10.0f);
    		playWord.setTextColor(Color.rgb( 100, 200, 200));
    		playWord.setOnClickListener(this);
    		tr.addView(playWord, 30,30);
    		
    		resignGame.setText("Resign");
    		resignGame.setTextSize(10.0f);
    		resignGame.setTextColor(Color.rgb( 100, 200, 200));
    		resignGame.setOnClickListener(this);
    		tr.addView(resignGame, 30,30);
	        
    		return buttonGrid;
	        /*
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
	        */
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