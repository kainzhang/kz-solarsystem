package me.lokka.solar.entity;

import me.lokka.solar.client.SolarSystemClient;
import me.lokka.solar.util.ImageUtil;

import java.awt.*;

public class Planet extends Star {

    private boolean ccw = true; // 是否逆时针
    private boolean isSmallPlanet = false; // 是否为小行星
    private SolarSystemClient ssc;

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

    public Planet(SolarSystemClient ssc, Star center, String imgKey, String name, double AU, double e, int T) {
        this.ssc = ssc;
        this.center = center;
        //this.img = GameUtil.getImage(imgName);
        this.img = ImageUtil.getImage(imgKey);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

        this.name = name; // 行星名称
        this.longAxis = getLongAxis(AU); // 长轴：天文单位
        this.shortAxis = getShortAxis(e); // 短轴：离心率和长轴
        this.speed = getSpeed(T);
        this.theta = 0.0;

        this.x = this.center.x + this.center.width / 2 + this.longAxis - this.width / 2;
        this.y = this.center.y;
    }

    public Planet(SolarSystemClient ssc, Star center, String imgName, String name, double AU, double e, int T, boolean ccw) {
        this(ssc, center, imgName, name, AU, e, T);
        this.ccw = ccw;
    }

    public Planet(SolarSystemClient ssc, boolean isSmallPlanet, Star center, String imgName, String name, double AU, double e, int T) {
        this(ssc, center, imgName, name, AU, e, T);
        this.isSmallPlanet = isSmallPlanet;
    }

    private double getSpeed(int T) {
        return 0.1 * 365 / T;
    }

    private int getLongAxis(double AU) {
        return (int) (100 * AU / 6);
    }

    private int getShortAxis(double e) {
        return (int) (longAxis * Math.sqrt(1 - e * e));
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
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
        g.drawString(name, x + width / 2, y + height / 2);
        g.setColor(c);
        g.setFont(f);
        move();
    }

    public void drawTrace(Graphics g) {
        int x = (int) (center.x + center.width / 2
                + Math.sqrt((longAxis * ssc.rate) * (longAxis * ssc.rate) - (shortAxis * ssc.rate) * (shortAxis * ssc.rate))
                - longAxis * ssc.rate);
        int y = center.y + center.height / 2 - shortAxis * ssc.rate;
        int width = 2 * longAxis * ssc.rate;
        int height = 2 * shortAxis * ssc.rate;
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.drawOval(x, y, width, height);
        g.setColor(c);
    }

    @Override
    public void move() {
        x = (int) (center.x + center.width / 2
                + Math.sqrt((longAxis * ssc.rate) * (longAxis * ssc.rate) - (shortAxis * ssc.rate) * (shortAxis * ssc.rate))
                - width / 2
                - longAxis * ssc.rate * Math.cos(theta));
        y = (int) (this.center.y + this.center.height / 2
                - this.height / 2
                + shortAxis * ssc.rate * Math.sin(theta));
        if (ccw) {
            this.theta -= speed;
        } else {
            this.theta += speed;
        }
    }
}
