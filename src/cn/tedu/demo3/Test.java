package cn.tedu.demo3;

import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Pool pool = new Pool();
		frame.add(pool);
		frame.setTitle("гуЬС");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		pool.start();
	}
}
