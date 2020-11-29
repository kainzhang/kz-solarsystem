package me.lokka.solar.util;

import me.lokka.solar.constant.Constant;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
    /**
     * 自定义生成窗口的方法
     */
    public void loadFrame() {
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(0, 0);
        // default value: false
        this.setVisible(true);
        // default value: true
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 启动线程
        new MyThread().start();
    }

    /**
     * 添加重画窗口的主线程
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            for( ; ; ) {
                repaint(); // 调用paint(Graphics g)一次
                try {
                    Thread.sleep(40);  // 每隔40ms刷新一次窗口
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
    Image backImg = null;

    // 重写update()方法，在窗口的里层添加一个虚拟的图片
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            // 如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }
        // 获取到虚拟图片的画笔
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.WHITE);
        backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        backg.setColor(c);
        // 调用虚拟图片的paint()方法，每50ms刷新一次
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }

}
