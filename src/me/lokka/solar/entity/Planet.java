package me.lokka.solar.entity;

import me.lokka.solar.constant.Constant;
import me.lokka.solar.util.GameUtil;

import java.awt.*;

public class Planet extends Star implements Moveable, Drawable {

    private boolean CCW = true; // 是否逆时针
    private boolean isSmallPlanet = false; // 是否为小行星

//    public Planet() {
//        this.img = GameUtil.getImage("earth.png");
//        this.width = img.getWidth(null);
//        this.height = img.getHeight(null);
//        this.longAxis = 400;
//        this.shortAxis = 150;
//        this.x = Constant.GAME_WIDTH / 2 + this.longAxis;
//        this.y = Constant.GAME_HEIGHT / 2;
//        this.name = "earth";
//        this.speed = 0.01;
//        this.theta = 0.0;
//    }

    public Planet(Star center, String imgName, String name, double AU, double e, int T) {
        this.center = center;
        this.img = GameUtil.getImage(imgName);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

        this.name = name; // 行星名称
        this.longAxis = getLongAxis(AU); // 长轴：天文单位
        this.shortAxis = getShortAxis(e); // 短轴：离心率和长轴
        this.speed = getSpeed(T) / 5;
        this.theta = 0.0;

        this.x = this.center.x + this.center.width / 2 + this.longAxis - this.width / 2;
        this.y = this.center.y;
    }

    public Planet(Star center, String imgName, String name, double AU, double e, int T, boolean CCW) {
        this(center, imgName, name, AU, e, T);
        this.CCW = CCW;
    }

    public Planet(boolean isSmallPlanet, Star center, String imgName, String name, double AU, double e, int T) {
        this(center, imgName, name, AU, e, T);
        this.isSmallPlanet = isSmallPlanet;
    }

    private double getSpeed(int T) {
        return 0.1 * 365 / T;
    }

    private int getLongAxis(double AU) {
        return (int) (100 * AU * 4);
    }

    private int getShortAxis(double e) {
        return (int) (this.longAxis * Math.sqrt(1 - e * e) / 2);
    }

    @Override
    public void draw(Graphics g) {
        if (!isSmallPlanet) {
            drawTrace(g); // 先画轨道
        }
        g.drawImage(img, x, y, this.width, this.height, null);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        Font f = g.getFont();
        g.setFont(new Font("微软雅黑", Font.BOLD, 15));
        g.drawString(name, x, y);
        g.setColor(c);
        g.setFont(f);
        move();
    }

    public void drawTrace(Graphics g) {
        int x = this.center.x + this.center.width / 2 - this.longAxis;
        int y = this.center.y + this.center.height / 2 - this.shortAxis;
        int width = 2 * this.longAxis;
        int height = 2 * this.shortAxis;
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawOval(x, y, width, height);
        g.setColor(c);
    }

    @Override
    public void move() {
        x = (int) (this.center.x + this.center.width / 2 - this.width / 2 + longAxis * Math.cos(theta));
        y = (int) (this.center.y + this.center.height / 2 - this.height / 2 + shortAxis * Math.sin(theta));
        if (CCW) {
            this.theta -= speed;
        } else {
            this.theta += speed;
        }
    }
}
