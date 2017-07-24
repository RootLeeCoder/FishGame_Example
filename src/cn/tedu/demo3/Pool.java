package cn.tedu.demo3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * 鱼塘类
 */
public class Pool extends JPanel implements MouseListener, MouseMotionListener {
	Net net = new Net();// 创建渔网对象
	Fish[] allFishs = new Fish[22];// 22条鱼
	BufferedImage background;// 背景图对象
	int score = 100; // 初始化分数
	Fish fish;

	public Pool() {
		// 注册监听
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		try {
			// 把背景图加载到内存中
			background = ImageIO.read(new File("bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 初始鱼对象
		for (int i = 0; i < 9; i++) {
			// fish01_00.png---> fish01_09.png
			// (i+1) 鱼图片从fish01开始
			// 初始化 0-17下标的鱼
			allFishs[i] = new Fish("fish0" + (i + 1));
			allFishs[i + 9] = new Fish("fish0" + (i + 1));
		}
		allFishs[18] = new Fish("fish13");
		allFishs[19] = new Fish("fish13");
		allFishs[20] = new Fish("fish14");
		allFishs[21] = new Fish("fish14");
		fish = allFishs[0];

		net.show = true;
	}

	public void start() { // 所有鱼的启动方法
		for (int i = 0; i < allFishs.length; i++) {
			// 把每条鱼线程启动
			new Thread(allFishs[i]).start();
		}
		while (true) {
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("隶书", Font.BOLD, 25));
		// 画背景
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < allFishs.length; i++) {
			Fish fish = allFishs[i];// 当前遍历到的鱼对象
			g.drawImage(fish.fish, fish.x, fish.y, fish.width, fish.height, null);
		}
		if (net.show) {
			g.drawImage(net.net, net.x - net.width / 2, net.y - net.height / 2, null);
		}
		g.drawString("score:  " + score, 30, 30);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		net.moveTo(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 单击时候触发
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//
		for (int i = 0; i < allFishs.length; i++) {
			if (net.catched(allFishs[i])) {
				allFishs[i].disappear();
			} else {

			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标进入事件源
		net.show = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 鼠标移出事件源
		net.show = false;
	}

}
