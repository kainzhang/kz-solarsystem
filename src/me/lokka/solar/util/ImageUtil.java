package me.lokka.solar.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 静态加载项目中的所有图片
 */
public class ImageUtil {
    private static Map<String, Image> images = new HashMap<>();

    static {
        images.put("bg", GameUtil.getImage("nasa.png"));

        images.put("sun", GameUtil.getImage("sun-real.png"));

        images.put("mercury", GameUtil.getImage("mercury.png"));
        images.put("venus", GameUtil.getImage("venus.png"));
        images.put("earth", GameUtil.getImage("earth.png"));
        images.put("moon", GameUtil.getImage("moon.png"));
        images.put("mars", GameUtil.getImage("mars.png"));
        images.put("jupiter", GameUtil.getImage("jupiter.png"));
        images.put("saturn", GameUtil.getImage("saturn.png"));
        images.put("uranus", GameUtil.getImage("uranus.png"));
        images.put("neptune", GameUtil.getImage("neptune.png"));
        images.put("pluto", GameUtil.getImage("pluto.png"));

        images.put("smallplanet", GameUtil.getImage("smallplanet.png"));

        images.put("ufo", GameUtil.getImage("ufo.png"));
        images.put("astronaut", GameUtil.getImage("astronaut.png"));
    }

    public static Image getImage(String key) {
        return images.get(key);
    }
}
