package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected String color;
    protected boolean style;
    protected boolean thickorthin;

    public Shape(int x, int y, String color, boolean style) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.style = style;
    }

    public abstract void updatePoint(int xe,int ye);
    public void setThikness(boolean thikness) {
        this.thickorthin = thikness;
    }
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor(color));
        if(this.style)
        {
            paint.setStyle(Paint.Style.STROKE);
        }
        else {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        if(thickorthin)
            paint.setStrokeWidth(75);
        else
            paint.setStrokeWidth(12);
    }



}