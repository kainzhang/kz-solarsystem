package me.lokka.solar.constant;

import java.awt.*;

public class Constant {
    /**
     * 窗口的宽度、高度
     */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int GAME_WIDTH = screenSize.width;
    public static final int GAME_HEIGHT = screenSize.height;

    /**
     * 图片路径前缀
     */
    public static final String IMGPATH_PRE = "me/lokka/solar/img/";
}
