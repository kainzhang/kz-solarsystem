package me.lokka.solar.util;

import me.lokka.solar.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * resource loading(img, audio
 */
public class GameUtil {
    public static Image getImage(String imgName) {
        // 通过反射机制，将资源路径下的资源转换成为字节码加载到内存中
        URL u = GameUtil.class.getClassLoader().getResource(Constant.IMGPATH_PRE + imgName);
        BufferedImage img = null;
        // load through I/O stream
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}

