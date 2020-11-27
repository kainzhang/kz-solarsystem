package me.lokka.solar.entity;

import me.lokka.solar.client.SolarSystemClient;
import me.lokka.solar.constant.Constant;
import me.lokka.solar.util.ImageUtil;

import java.awt.*;

public class Widget extends Star {

    private int centerX, centerY;

    public Widget(int centerX, int centerY, String imgKey, String name, double AU, double e, int T) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.img = ImageUtil.getImage(imgKey);
        this.width = this.img.getWidth(null);
        this.height = this.img.getHeight(null);
        this.name = name;

        this.x = this.centerX + this.longAxis - this.width / 2;
        this.y = this.centerY;

        this.longAxis = getLongAxis(AU); // 长轴：天文单位
        this.shortAxis = getShortAxis(e); // 短轴：离心率和长轴
        this.speed = getSpeed(T);
        this.theta = 0.0;
    }

    private double getSpeed(int T) {
        return 0.1 * 365 / T;
    }

    private int getLongAxis(double AU) {
        return (int) (100 * AU / 2);
    }

    private int getShortAxis(double e) {
        return (int) (longAxis * Math.sqrt(1 - e * e));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
        move();
    }

    @Override
    public void move() {
        x = (int) (centerX + Math.sqrt(longAxis * longAxis - shortAxis * shortAxis) - width / 2 - longAxis * Math.cos(theta));
        y = (int) (centerY - height / 2 + shortAxis * Math.sin(theta));
        theta -= speed;
    }
}
