package edu.upenn.cis350;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

/*
 * This class is a View that you can include in your Activity.
 * It uses very basic 2D graphics to draw a square and a circle.
 * Ooooooohhhh, pretty colors.....
 */

abstract class Shape{
	
	public abstract void draw(Paint paint, Canvas canvas);
	
	public abstract boolean inBounds(float x, float y);
	
	public abstract void setColor(int color);
	
}
class ColoredSquare extends Shape{
	
	private int _color = 0;
	private float _x = 0; 
	private float _y = 0;
	private int _side = 0;
	
	public ColoredSquare(int color, float x, float y, int side){
		_color = color;
		_x = x;
		_y = y;
		_side = side;
	}
	
	public void draw(Paint paint, Canvas canvas){
		paint.setColor(_color);
		canvas.drawRect(_side, _side, _x, _y, paint);
	}
	
	public boolean inBounds(float x, float y){
		if((x <= _x && x >= (_x - _side)) && (y <= _y && y >= (_y - _side)))
			return true;
		//Log.v("Rectangle", "Called");
		return false;
	}
	
	public void setColor(int color){
		_color=color;
	}
}
public class ShapesView extends View {
	private final String DEV_TAG="ShapesView";
	private ArrayList<Shape> _shapes = new ArrayList<Shape>();
	
	// you must implement these constructors!!
	public ShapesView(Context c) {
		super(c);
		_shapes.add(new ColoredSquare(Color.BLUE, 100, 100, 50));
	}
	public ShapesView(Context c, AttributeSet a) {
		super(c, a);
		_shapes.add(new ColoredSquare(Color.BLUE, 100, 100, 50));
	}
	
// This method is called when the View is displayed
	protected void onDraw(Canvas canvas) {
	
		// this is the "paintbrush"
		Paint paint = new Paint();
		// set the color
		//paint.setColor(Color.BLUE);
		
		// draw Rectangle with corners at (40, 20) and (90, 80)
		//canvas.drawRect(50, 50, 100, 100, paint);
		
		for(int i = 0; i < _shapes.size(); i++){
			_shapes.get(i).draw(paint, canvas);
		}
		
		/**
		// change the color
		paint.setColor(Color.BLUE);
		// set a shadow
		paint.setShadowLayer(10, 10, 10, Color.GREEN);
	
		
		// create a bounding rectangle
		RectF rect = new RectF(150, 150, 280, 280);
		// draw an oval in the bounding rectangle
		canvas.drawOval(rect, paint);
		**/
	} 
	
	public boolean onTouchEvent(MotionEvent event){
		float x = event.getX();
		float y = event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			Log.v(DEV_TAG, "Event");
			for(int i = 0; i < _shapes.size(); i++){
				if(_shapes.get(i).inBounds(x, y)){
					_shapes.get(i).setColor(Color.YELLOW);
				}
			}
			this.invalidate();
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			Log.v(DEV_TAG, "UpEvent");
			for(int i = 0; i < _shapes.size(); i++){
				if(_shapes.get(i).inBounds(x, y)){
					_shapes.get(i).setColor(Color.BLUE);
				}
			}
			this.invalidate();
			return true;
		}
		this.invalidate();
		return super.onTouchEvent(event);
	}
	

}
