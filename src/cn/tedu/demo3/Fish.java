package cn.tedu.demo3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * ����
 */
public class Fish implements Runnable {
	int x;
	int y; // ����
	int width;
	int height; // ��ͼƬ�Ŀ��
	int speed; // �ζ����ٶ�
	BufferedImage[] fishs = new BufferedImage[10];// ���һ��ͼƬ
	BufferedImage fish;// ��ǰ�����ͼƬ
	int index;// ��ͼƬ���л�
	Random rand = new Random();// ����Random �����������

	public Fish(String name) {// ��ʼ��
		for (int i = 0; i < 10; i++) {
			// ÿ��ͼƬ�������ڴ���
			// fish01_00.png
			try {
				fishs[i] = ImageIO.read(new File(name + "_0" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			fish = fishs[0];// Ĭ��ÿ�γ�ʼ��ȡ��һ��ͼƬ
			width = fish.getWidth();
			height = fish.getHeight();
			x = rand.nextInt(800 - width);
			y = rand.nextInt(600 - height);// �����������
			index = 0;
			speed = 8;
		}
	}

	public void move() {// ���ζ��ķ���
		// ��������޸� ��ͼƬ���л�
		fish = fishs[index++ % fishs.length];
		x = x - speed;
		if (x < -width) { //
			disappear();
		}
	}

	public void disappear() {// ����ʧ��Ĵ�����
		speed = rand.nextInt(8) + 1; // �ٶ���������
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
