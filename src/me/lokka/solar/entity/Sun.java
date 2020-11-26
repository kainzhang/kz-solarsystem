package me.lokka.solar.entity;

import me.lokka.solar.constant.Constant;
import me.lokka.solar.util.GameUtil;

import java.awt.*;

public class Sun extends Star {

    public Sun() {
        this.img = GameUtil.getImage("sun-real.png");
        this.width = this.img.getWidth(null) / 3;
        this.height = this.img.getHeight(null) / 3;
        this.x = /*Constant.GAME_WIDTH / 2*/ - this.width / 2 + 350;
        this.y = Constant.GAME_HEIGHT / 2 - this.height / 2 - 100;
        this.name = "太阳";
    }

    public Sun(String name) {
        // 调用本类的其他构造方法，必须在第一行
        this();
        this.name = name;
    }

    public void move() {

    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, this.width, this.height, null);
    }
}
