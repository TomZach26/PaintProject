package com.example.paint;

    public class AreaShape extends Shape{
        double area;
        public AreaShape(int x, int y, String color,boolean style) {
            super(x, y, color ,style);
            this.area = 0;

        }

        @Override
        public void updatePoint(int xe, int ye) {

        }

        public double getArea() {
            return area;
        }
    }

