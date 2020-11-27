package me.lokka.solar.client;

import me.lokka.solar.constant.Constant;
import me.lokka.solar.entity.Planet;
import me.lokka.solar.entity.Sun;
import me.lokka.solar.entity.Widget;
import me.lokka.solar.util.GameUtil;
import me.lokka.solar.util.ImageUtil;
import me.lokka.solar.util.MyFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SolarSystemClient extends MyFrame {

    public Image bg = ImageUtil.getImage("bg");

    public Sun sun = new Sun();

    public Planet mercury = new Planet(this, sun, "mercury", "Mercury", 0.387, 0.2056, 88);
    public Planet venus = new Planet(this, sun, "venus", "Venus", 0.72, 0.0068, 225, false);
    public Planet earth = new Planet(this, sun, "earth", "Earth", 1, 0.0167, 365);
    public Planet moon = new Planet(this, earth, "moon", "", 0.3, 0.0, 365 / 12);
    public Planet mars = new Planet(this, sun, "mars", "Mars", 1.52, 0.0934, 687);

    public Planet jupiter = new Planet(this, sun, "jupiter", "Jupiter", 5.2, 0.0489, 4330);
    public Planet saturn = new Planet(this, sun, "saturn", "Saturn", 9.54, 0.0557, 10832);
    public Planet neptune = new Planet(this, sun, "neptune", "Neptune", 19.218, 0.0444, 30777);
    public Planet uranus = new Planet(this, sun, "uranus", "Uranus", 30.06, 0.0112, 60328);
    public Planet pluto = new Planet(this, sun, "pluto", "Pluto", 39.68, 0.249, 90717);

    public Planet halley = new Planet(this, sun, "venus", "Halley", 35.1, 0.967, 485);

    public List<Planet> planets = new ArrayList<>();
    {
        // 创建1000个小行星，形成小行星带（AU：2.17 ~ 3.64)
        for (int i = 0; i < 1000; i++) {
            Planet smallPlanet = new Planet(this, true, sun, "smallplanet", "", Math.random() * 2 + 2, 0.0, (int) (Math.random() * 3300 + 700));
            planets.add(smallPlanet);
        }
    }

    public Widget ufo = new Widget(Constant.GAME_WIDTH - 400, Constant.GAME_HEIGHT + 120, "ufo", "UFO", 22, 0.1, 1000);
    public Widget astronaut = new Widget(Constant.GAME_WIDTH - 500, Constant.GAME_HEIGHT - 250, "astronaut", "Astronaut", 1, 0.9, 500);

    /**
     * 默认半轴长为 4 倍长度
     */
    public int rate = 4;

    @Override
    public void loadFrame() {
        this.setTitle("Solar System");
        this.setIconImage(GameUtil.getImage("logo.png"));
        super.loadFrame();

        // 添加键盘监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // 按 ⬆️ 键 rate 增加2倍
                // 按 ⬇️ 键 rate 缩小2倍
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        rate *= 2;
                        break;
                    case KeyEvent.VK_DOWN:
                        rate /= 2;
                        break;
                    default:
                        break;
                }
                if (rate <= 1) rate = 1;
                if (rate >= 1024) rate = 1024;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        sun.draw(g);
        halley.draw(g);

        // 近日行星
        mercury.draw(g);
        venus.draw(g);
        earth.draw(g);
        moon.draw(g);
        mars.draw(g);

        // 小行星带
        for (Planet planet : planets) {
            planet.draw(g);
        }

        // 巨行星
        jupiter.draw(g);
        saturn.draw(g);
        neptune.draw(g);

        // 远日行星
        uranus.draw(g);
        pluto.draw(g);

        ufo.draw(g);
        astronaut.draw(g);

        Font f = g.getFont();
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Zoom: " + rate + "x", 100, 100);
        g.setColor(c);
        g.setFont(f);
    }

    public static void main(String[] args) {
        new SolarSystemClient().loadFrame();
    }
}
