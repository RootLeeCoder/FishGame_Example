package cn.tedu.demo3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * ������
 */
public class Net {
	int x;
	int y; // ����
	int width;
	int height; // ͼƬ�Ŀ��
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
		// ���񷽷�
		int dx = this.x - fish.x;
		int dy = this.y - fish.y;
		return dx > 0 && dx < fish.width && dy > 0 && dy < fish.height;

	}

	public void moveTo(int x, int y) {// �ӽ������������ʵʱ���괫�ݹ���
		this.x = x;
		this.y = y;
	}
}
