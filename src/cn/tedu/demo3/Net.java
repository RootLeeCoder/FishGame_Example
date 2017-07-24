package cn.tedu.demo3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 渔网类
 */
public class Net {
	int x;
	int y; // 坐标
	int width;
	int height; // 图片的宽高
	BufferedImage net;
	boolean show;//

	public Net() {
		try {
			net = ImageIO.read(new File("net09.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = net.getWidth();
		height = net.getHeight();
		show = true;
	}

	public boolean catched(Fish fish) {
		// 捕获方法
		int dx = this.x - fish.x;
		int dy = this.y - fish.y;
		return dx > 0 && dx < fish.width && dy > 0 && dy < fish.height;

	}

	public void moveTo(int x, int y) {// 从界面把坐标鼠标的实时坐标传递过来
		this.x = x;
		this.y = y;
	}
}
