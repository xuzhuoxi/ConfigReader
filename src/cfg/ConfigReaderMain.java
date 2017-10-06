package cfg;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConfigReaderMain {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigReaderMain window = new ConfigReaderMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfigReaderMain() {
		initialize();
		System.out.println("我要打印当前路径啦");
		System.out.println(AppDefine.instance.getBasePath());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton button = new JButton("测试");
		button.setBounds(170, 211, 93, 23);
		frame.getContentPane().add(button);
	}
}
