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
 * ������
 */
public class Pool extends JPanel implements MouseListener, MouseMotionListener {
	Net net = new Net();// ������������
	Fish[] allFishs = new Fish[22];// 22����
	BufferedImage background;// ����ͼ����
	int score = 100; // ��ʼ������
	Fish fish;

	public Pool() {
		// ע�����
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		try {
			// �ѱ���ͼ���ص��ڴ���
			background = ImageIO.read(new File("bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ʼ�����
		for (int i = 0; i < 9; i++) {
			// fish01_00.png---> fish01_09.png
			// (i+1) ��ͼƬ��fish01��ʼ
			// ��ʼ�� 0-17�±����
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

	public void start() { // ���������������
		for (int i = 0; i < allFishs.length; i++) {
			// ��ÿ�����߳�����
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
		g.setFont(new Font("����", Font.BOLD, 25));
		// ������
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < allFishs.length; i++) {
			Fish fish = allFishs[i];// ��ǰ�������������
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
		// ����ʱ�򴥷�
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
		// �������¼�Դ
		net.show = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// ����Ƴ��¼�Դ
		net.show = false;
	}

}
