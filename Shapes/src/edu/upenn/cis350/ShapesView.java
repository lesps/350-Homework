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

	public abstract void move(float x, float y);

	public abstract boolean inBounds(float x, float y);

	public abstract void setColor(int color);

}

class ColoredSquare extends Shape{

	private int _color = 0;
	private float _x1 = 0; 
	private float _y1 = 0;
	private float _x2 = 0;
	private float _y2 = 0;

	public ColoredSquare(int color, float x1, float y1, float x2, float y2){
		_color = color;
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}

	public void draw(Paint paint, Canvas canvas){
		paint.setColor(_color);
		canvas.drawRect(_x1, _y1, _x2, _y2, paint);
	}

	/**
	 * Detects whether given coordinates are in-bounds of this rectangle
	 * @param x The x-coordinate
	 * @param y the y-coordinate
	 * @return Whether this is in bounds or not
	 */
	public boolean inBounds(float x, float y){
		if((x >= _x1 && x <= _x2) && (y >= _y1 && y <= _y2))
			return true;
		//Log.v("Rectangle", "Called");
		return false;
	}

	public void setColor(int color){
		_color=color;
	}

	/**
	 * Moves the shape
	 * @param x Difference in x
	 * @param y Difference in y
	 */
	public void move(float x, float y){
		_x1 += x;
		_y1 += y;
		_x2 += x;
		_y2 += y;
	}

}
public class ShapesView extends View {
	private final String DEV_TAG="ShapesView";
	private ArrayList<Shape> _shapes = new ArrayList<Shape>();
	private float _lastX=-1;
	private float _lastY=-1;
	private Shape shape= null;
	// you must implement these constructors!!
	public ShapesView(Context c) {
		super(c);
		_shapes.add(new ColoredSquare(Color.BLUE, 50, 50, 100, 100));
	}
	public ShapesView(Context c, AttributeSet a) {
		super(c, a);
		_shapes.add(new ColoredSquare(Color.BLUE, 50, 50, 100, 100));
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
		if(_lastX == -1)
			_lastX = x;
		if(_lastY == -1)
			_lastY = y;
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			Log.v(DEV_TAG, "Event");
			for(int i = 0; i < _shapes.size(); i++){
				if(_shapes.get(i).inBounds(x, y)){
					shape = _shapes.get(i);
					break;
				}
			}
			if(shape == null)
				return false;
			shape.setColor(Color.YELLOW);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			if(shape == null)
				return false;
			shape.setColor(Color.BLUE);
			_lastX=-1;
			_lastY=-1;
			shape = null;
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
			if(shape == null)
				return false;
			shape.move((x - _lastX), (y - _lastY));

			_lastX = x;
			_lastY = y;
			
		}
		this.invalidate();
		return true;
	}


}
