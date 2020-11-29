package me.lokka.solar.entity;

import me.lokka.solar.constant.Constant;
import me.lokka.solar.util.ImageUtil;

import java.awt.*;

public class Meteor extends Star {

    private final int originX;
    private final int originY;

    public Meteor(int x, int y, double theta, int speed, String imgKey) {
        this.x = x;
        this.originX = x;
        this.y = y;
        this.originY = y;
        this.theta = theta;
        this.speed = speed;
        this.img = ImageUtil.getImage(imgKey);
        double rate = Math.random();
        this.width = (int) (img.getWidth(null) * rate);
        this.height = (int) (img.getHeight(null) * rate);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
        move();
    }

    @Override
    public void move() {
        x = x - (int) (speed * Math.cos(theta));
        y = y + (int) (speed * Math.sin(theta));
        if (x >= Constant.GAME_WIDTH || x <= -width) {
            x = originX;
        }
        if (y >= Constant.GAME_HEIGHT) {
            y = originY;
        }
    }
}
