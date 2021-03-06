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
	private float _x1 = 0; 
	private float _y1 = 0;
	private float _x2 = 0;
	private float _y2 = 0;
	private int _color = 0;

	public abstract void draw(Paint paint, Canvas canvas);

	public Shape(float x1, float y1, float x2, float y2, int color){
		_x1 = x1;
		_x2 = x2;
		_y1 = y1;
		_y2 = y2;
		_color = color;
	}
	public void move(float x, float y){
		_x1 += x;
		_y1 += y;
		_x2 += x;
		_y2 += y;
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
	
	/**
	 * Returns true if this shape intersects the other shape, false otherwise
	 * @param other The shape to check intersection for
	 * @return Whether this shape intersects the other or not
	 */
	public boolean intersects(Shape other){
		if(inBounds(other.getX1(), other.getY1()))
			return true;
		else if(inBounds(other.getX2(), other.getY2()))
			return true;
		else if(inBounds(other.getX1(), other.getY2()))
			return true;
		else if(inBounds(other.getX2(), other.getY1()))
			return true;
		else if(other.inBounds(this.getX1(), this.getY1()))
			return true;
		else if(other.inBounds(this.getX2(), this.getY2()))
			return true;
		else if(other.inBounds(this.getX1(), this.getY2()))
			return true;
		else if(other.inBounds(this.getX2(), this.getY1()))
			return true;
		
		
		
		return false;
	}

	public void setColor(int color){
		_color = color;
	}
	
	public void setPosition(float x1, float x2, float y1, float y2){
		_x1 = x1;
		_x2 = x2;
		_y1 = y1;
		_y2 = y2;
	}
	
	public float getX1(){
		return _x1;
	}
	
	public float getX2(){
		return _x2;
	}
	
	public float getY1(){
		return _y1;
	}
	
	public float getY2(){
		return _y2;
	}
	
	public int getColor(){
		return _color;
	}
}

class ColoredRectangle extends Shape{
	

	public ColoredRectangle(float x1, float y1, float x2, float y2, int color){
		super(x1, y1, x2, y2, color);
	}

	public void draw(Paint paint, Canvas canvas){
		paint.setColor(getColor());
		canvas.drawRect(getX1(), getY1(), getX2(), getY2(), paint);
	}

	

}
public class ShapesView extends View {
	private final String DEV_TAG="ShapesView";
	public static final String DEBUG = "SHAPE";
	private ArrayList<Shape> _shapes = new ArrayList<Shape>();
	private float _lastX=-1;
	private float _lastY=-1;
	private float _originalX1 = -1;
	private float _originalY1 = -1;
	private float _originalX2 = -1;
	private float _originalY2 = -1;
	private Shape shape= null;
	// you must implement these constructors!!
	public ShapesView(Context c) {
		super(c);
		_shapes.add(new ColoredRectangle(50, 50, 100, 100, Color.BLUE));
		_shapes.add(new ColoredRectangle(150, 150, 300, 300, Color.BLUE));
	}
	public ShapesView(Context c, AttributeSet a) {
		super(c, a);
		_shapes.add(new ColoredRectangle(50, 50, 100, 100, Color.BLUE));
		_shapes.add(new ColoredRectangle(150, 150, 300, 300, Color.BLUE));
	}
	
	public void poop(){
		Log.v(DEBUG,"I love poop");
	}

	// This method is called when the View is displayed
	protected void onDraw(Canvas canvas) {

		// this is the "paintbrush"
		Paint paint = new Paint();
		
		for(int i = 0; i < _shapes.size(); i++){
			_shapes.get(i).draw(paint, canvas);
		}

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
					_originalX1 = shape.getX1();
					_originalX2 = shape.getX2();
					_originalY1 = shape.getY1();
					_originalY2 = shape.getY2();
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
			for(int i = 0; i < _shapes.size(); i++){
				if(_shapes.get(i) == shape)
					continue;
				if(_shapes.get(i).intersects(shape)){
					shape.setPosition(_originalX1, _originalX2, _originalY1, _originalY2);
					break;
				}
			}
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
