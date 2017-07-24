package cn.tedu.demo3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * 鱼类
 */
public class Fish implements Runnable {
	int x;
	int y; // 坐标
	int width;
	int height; // 鱼图片的宽高
	int speed; // 游动的速度
	BufferedImage[] fishs = new BufferedImage[10];// 鱼的一组图片
	BufferedImage fish;// 当前的鱼的图片
	int index;// 鱼图片的切换
	Random rand = new Random();// 创建Random 对象，用于随机

	public Fish(String name) {// 初始化
		for (int i = 0; i < 10; i++) {
			// 每张图片加载入内存中
			// fish01_00.png
			try {
				fishs[i] = ImageIO.read(new File(name + "_0" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			fish = fishs[0];// 默认每次初始化取第一张图片
			width = fish.getWidth();
			height = fish.getHeight();
			x = rand.nextInt(800 - width);
			y = rand.nextInt(600 - height);// 生成坐标随机
			index = 0;
			speed = 8;
		}
	}

	public void move() {// 鱼游动的方法
		// 横坐标的修改 ，图片的切换
		fish = fishs[index++ % fishs.length];
		x = x - speed;
		if (x < -width) { //
			disappear();
		}
	}

	public void disappear() {// 鱼消失后的处理方法
		speed = rand.nextInt(8) + 1; // 速度重新生成
		x = 800;
	}

	@Override
	public void run() {
		while (true) {
			move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
