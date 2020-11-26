package me.lokka.solar.client;

import me.lokka.solar.entity.Planet;
import me.lokka.solar.entity.Sun;
import me.lokka.solar.util.GameUtil;
import me.lokka.solar.util.MyFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SolarSystemClient extends MyFrame {

    public Image bg = GameUtil.getImage("nasa.png");

    public Sun sun = new Sun();

    public Planet mercury = new Planet(sun, "mercury.png", "Mercury", 0.387, 0.2056, 88);
    public Planet venus = new Planet(sun, "venus.png", "Venus", 0.72, 0.0068, 225, false);
    public Planet earth = new Planet(sun, "earth.png", "Earth", 1, 0.0167, 365);

    public Planet moon = new Planet(earth, "moon.png", "", 0.3, 0.0, 365 / 12);

    public Planet mars = new Planet(sun, "mars.png", "Mars", 1.52, 0.0934, 687);

    public Planet jupiter = new Planet(sun, "jupiter.png", "Jupiter", 5.2, 0.0489, 4330);
    public Planet saturn = new Planet(sun, "saturn.png", "Saturn", 9.54, 0.0557, 10832);
    public Planet neptune = new Planet(sun, "neptune.png", "Neptune", 19.218, 0.0444, 30777);
    public Planet uranus = new Planet(sun, "uranus.png", "Uranus", 30.06, 0.0112, 60328);

    public Planet pluto = new Planet(sun, "pluto.png", "Pluto", 39.68, 0.249, 90717);

    public List<Planet> planets = new ArrayList<>();
    {
        // 创建1000个小行星，形成小行星带（AU：2.17 ~ 3.64)
        for (int i = 0; i < 4000; i++) {
            Planet smallPlanet = new Planet(true, sun, "smallplanet.png", "", Math.random() * 2 + 2, 0.0, (int) (Math.random() * 3300 + 700));
            planets.add(smallPlanet);
        }
    }

    @Override
    public void loadFrame() {
        this.setTitle("Solar System");
        this.setIconImage(GameUtil.getImage("logo.png"));
        super.loadFrame();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        sun.draw(g);

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

        Font f = g.getFont();
        g.setFont(new Font("微软雅黑", Font.BOLD, 35));
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("Long Axis: " + earth.longAxis, 100, 100);
//        g.drawString("Short Axis: " + earth.shortAxis, 100, 150);
        g.setColor(c);
        g.setFont(f);
    }

    public static void main(String[] args) {
        new SolarSystemClient().loadFrame();
    }
}
