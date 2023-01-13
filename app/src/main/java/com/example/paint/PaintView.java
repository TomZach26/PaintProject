package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {

    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";
    private boolean currentThickness;
    private double maxarea=0;
    private Shape max;

    private Stack<Shape> shapes;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        bgPaint.setColor(Color.rgb(255,255,255));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }
    boolean currentStyle = true;

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor, currentStyle);
            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor, currentStyle);
            }
                if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor,currentStyle);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor,currentStyle);
            }

            shape.setThikness(currentThickness);

            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }


    public void setstyle(boolean style){
        currentStyle = style;
    }

    public void toggleThickness(){
        currentThickness =!currentThickness;
    }


    public void RemoveAllButTheBiggest()
    {
        for (int i = 0; i < shapes.size(); i++){
            if(shapes.get(i) instanceof AreaShape) {
                AreaShape shape= (AreaShape) shapes.get(i);
                if(shape.getArea()>maxarea){
                    maxarea=shape.getArea();
                    max = shape;
                }
            }
        }
        for (int i = shapes.size(); i > 0; i--){
            shapes.pop();
            invalidate();
        }
        if(max!=null)
            shapes.push(max);
    }

    public void undo()
    {
        shapes.pop();
        invalidate();
    }
}
