package android.scrabble;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class GameBoardActivity extends Activity implements OnClickListener{
	
	private TextView playerOne, playerTwo, playerOnePoints, playerTwoPoints; 
	private Button swapLetters, playWord, resignGame, pass, shuffle;
	private Button[] playerLetters;
    private Button[][] gameBoard;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        playerOne = new TextView(this);
	        playerTwo = new TextView(this);
	        playerOnePoints = new TextView(this);
	        playerTwoPoints = new TextView(this);
	        
	        swapLetters = new Button(this);
	        playWord = new Button(this);
	        resignGame = new Button(this);
	        pass = new Button(this);
	        shuffle = new Button(this);
	       
	        ScrollView mainScrollLayout = new ScrollView(this);
	        
	        HorizontalScrollView horScrollLayout = new HorizontalScrollView(this);
	        
	        TableLayout la = new TableLayout(this);
	        
	        TableLayout masterLayout = new TableLayout(this);
	                
	        //Adding the board to the main layout
	        la.addView(createBoardGrid());
	        la.addView(createCharGrid());
	        la.addView(createPlayerGrid());
	        la.addView(createButtonGrid());
	        
	        horScrollLayout.addView(la);
	        mainScrollLayout.addView(horScrollLayout);
	        
	        masterLayout.addView(mainScrollLayout);
	        
	        super.setContentView(masterLayout);
	    }
	   
	    public TableLayout createBoardGrid() {
	        TableLayout boardGrid = new TableLayout(this);

	        boardGrid.setLayoutParams(new TableLayout.LayoutParams());
	        
	        boardGrid.setPadding(1, 1, 1, 1);
	        
	        int x = 100;
   		 	int y = 100;
	        
   		 	for (int i = 0; i < 15; i++) {
	        	TableRow tr = new TableRow(this);
	        	for (int n = 0; n <15; n++) {
	        		 Button b = new Button(this);
	        		 
	        		 gameBoard[i][y] = b;
	                 
	        		 //Setting the id for each button in the form of 1xx1yy
	        		 int xx = x + n;
	        		 int yy = y + i; 
	        		 b.setId(xx*1000+yy);
	        		 
	        		 b.setText(""+i+n);
	                 b.setTextSize(10.0f);
	                 b.setTextColor(Color.rgb(0, 0, 0));
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
	        		
	        		playerLetters[n] = b;
	        		
	        		b.setId(n);        		
	        		b.setText(""+i+n); //Need to set the character here
	        		b.setTextSize(10.0f);
	        		b.setTextColor(Color.rgb(0, 0, 0));
	        		b.setBackgroundColor(Color.rgb(100, 149, 237));
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
        	
        	playerOne.setText("Player1");	//Need to get the playerName from somewhere
    		playerOne.setTextSize(10.0f);
    		playerOne.setTextColor(Color.rgb(255, 255, 255));
    		tr1.addView(playerOne);
    		
    		playerOnePoints.setText(" 0");	//Need to get the playerOnePoints from somewhere
    		playerOnePoints.setTextSize(10.0f);
    		playerOnePoints.setTextColor(Color.rgb(255, 255, 255));
    		tr1.addView(playerOnePoints);
    		
    		playerTwo.setText("Player2");	//Need to get the playerName from somewhere
    		playerTwo.setTextSize(10.0f);
    		playerTwo.setTextColor(Color.rgb(255, 255, 255));
    		tr2.addView(playerTwo);
    		
        	playerTwoPoints.setText(" 0");	//Need to get the playerTwoPoints from somewhere
    		playerTwoPoints.setTextSize(10.0f);
    		playerTwoPoints.setTextColor(Color.rgb(255, 255, 255));
    		tr2.addView(playerTwoPoints);
    		
    		playerGrid.addView(tr1);
    		playerGrid.addView(tr2);
	    	
    		return playerGrid;
    		/*
    		for (int i = 0; i < 2; i++) {
	        	for(int n = 0; i < 2; i++) {
	        		TextView tw = new TextView(this);
	        		tw.setText(""+i+n);
	        		tw.setTextSize(10.0f);
	        		tw.setTextColor(Color.rgb(255, 255, 255));
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
	        
	        playWord.setText("Play word");
    		playWord.setTextSize(10.0f);
    		playWord.setTextColor(Color.rgb(0, 0, 0));
    		playWord.setOnClickListener(this);
    		tr.addView(playWord);
    		
    		pass.setText("Pass");
    		pass.setTextSize(10.0f);
    		pass.setTextColor(Color.rgb(0, 0, 0));
    		pass.setOnClickListener(this);
    		tr.addView(pass);
    		
    		shuffle.setText("Shuffle");
    		shuffle.setTextSize(10.0f);
    		shuffle.setTextColor(Color.rgb(0, 0, 0));
    		shuffle.setOnClickListener(this);
    		tr.addView(shuffle);
    		
    		swapLetters.setText("Swap letters");
    		swapLetters.setTextSize(10.0f);
    		swapLetters.setTextColor(Color.rgb(0, 0, 0));
    		swapLetters.setOnClickListener(this);
    		tr.addView(swapLetters);
    		
    		resignGame.setText("Resign");
    		resignGame.setTextSize(10.0f);
    		resignGame.setTextColor(Color.rgb(0, 0, 0));
    		resignGame.setOnClickListener(this);
    		tr.addView(resignGame);
	        
    		buttonGrid.addView(tr);
    		
    		return buttonGrid;
	        /*
	        for (int i = 0; i < 1; i++) {
	        	TableRow tr = new TableRow(this);
	        	for(int n = 0; i < 3; i++) {
	        		Button b = new Button (this);
	        		b.setText(""+i+n);
	        		b.setTextSize(10.0f);
	        		b.setTextColor(Color.rgb(255, 255, 255));
	        		b.setOnClickListener(this);
	        		tr.addView(b, 30,30);
	        	}
	        buttonGrid.addView(tr);
	        }
	        */
	    }
	    
	    /**This method is used to set new characters when some have been played*/
	    public void setLetter(char c, int x) {
	    	playerLetters[x].setText(c);
	    }
	    
	    /**This method is used to set new characters on the board*/
	    public void setLetterOnBoard(char c, int x, int y) {
	    	gameBoard[x][y].setText(c);
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