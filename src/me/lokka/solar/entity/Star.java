package me.lokka.solar.entity;

import java.awt.*;

public abstract class Star implements Moveable, Drawable{
    public int x;
    public int y;
    public String name;
    public Image img;
    public int longAxis;
    public int shortAxis;
    public double speed;
    public double theta;

    public int width;
    public int height;

    // 环绕中心
    public Star center;

}
