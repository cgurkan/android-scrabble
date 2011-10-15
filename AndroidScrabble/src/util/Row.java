package util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Row extends View{
    
	private OpponentData od;
	
	public Row(Context context) {
        super(context);
    }
	
	public void setOpponentData(OpponentData o){
		this.od = o;
	}
	
	/*@Override
	public void onMeasure(int w, int h){
		super.onMeasure(w, h);
	}*/
	
    @Override
    public void onDraw(Canvas c) {
    	if(od == null){
    		return;
    	}
    	c.drawColor(Color.CYAN);
    	
    	Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		paint.setColor(Color.WHITE);
		c.drawText(od.getUsername(), 0, 0, paint);
		
		String status = "";			
		if(od.isOnline()){
			status = "Online";
			paint.setColor(Color.GREEN);
		}
		else{
			status = "Offline";
			paint.setColor(Color.RED);
		}
		c.drawText(status, 0, 15, paint);
    }
}