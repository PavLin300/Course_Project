package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.net.URL;
import java.util.Queue;

import javax.swing.JFrame;

import models.Counter;
import models.Creator;
import models.Handler;


import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui {

	private JFrame frame;
	private JButton btnStartPlayer;
	private JTextField textFieldCounter;
	private JTextField textFieldRefuseCounter;
	private JLabel lblCreator1;
	private JLabel lblCreator2;
	private JLabel lblHandler1;
	private JLabel lblHandler2;
	private long startTime;
	private Object thPlay;
	private Thread tc1;
	private Thread tc2;
	private Thread th1;
	private Thread th2;
	private Thread player;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
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
	public MainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnStartPlayer = new JButton("New button");
		btnStartPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doRun();
			}
		});
		btnStartPlayer.setBounds(91, 188, 230, 46);
		frame.getContentPane().add(btnStartPlayer);
		
		textFieldCounter = new JTextField();
		textFieldCounter.setBounds(115, 46, 86, 20);
		frame.getContentPane().add(textFieldCounter);
		textFieldCounter.setColumns(10);
		
		textFieldRefuseCounter = new JTextField();
		textFieldRefuseCounter.setColumns(10);
		textFieldRefuseCounter.setBounds(235, 146, 86, 20);
		frame.getContentPane().add(textFieldRefuseCounter);
		
		lblCreator1 = new JLabel("New label");
		lblCreator1.setBounds(79, 102, 46, 14);
		frame.getContentPane().add(lblCreator1);
		
		lblCreator2 = new JLabel("New label");
		lblCreator2.setBounds(282, 82, 46, 14);
		frame.getContentPane().add(lblCreator2);
		
		lblHandler1 = new JLabel("New label");
		lblHandler1.setBounds(67, 137, 46, 14);
		frame.getContentPane().add(lblHandler1);
		
		lblHandler2 = new JLabel("New label");
		lblHandler2.setBounds(141, 137, 46, 14);
		frame.getContentPane().add(lblHandler2);
	}

	public Container getPane() {
		// TODO Auto-generated method stub
		return frame.getContentPane();
		
	}
	
	public boolean isPlaying() {
		return true; // TODO: ADD MUSIC
	}
	
	public boolean isCreatorWorking() {
		return true;
	}

	public JButton getBtnStartPlayer() {
		return btnStartPlayer;
	}
	
	
	protected void doRun() {
		btnStartPlayer.setEnabled(false);
		Counter counter = new Counter(textFieldCounter);
		Counter refuseCounter = new Counter(textFieldRefuseCounter);
		models.Queue queue = new models.Queue(this, refuseCounter);
		Creator creator1 = new Creator(this, lblCreator1, queue);
		Creator creator2 = new Creator(this, lblCreator2, queue);
		Handler handler1 = new Handler(this, lblHandler1, queue);
		Handler handler2 = new Handler(this, lblHandler2, queue);
		startTime = System.currentTimeMillis();
		thPlay = playMusic();
		(tc1 = new Thread(creator1)).start();
		(tc2 = new Thread(creator2)).start();
		(th1 = new Thread(handler1)).start();
		(th2 = new Thread(handler2)).start();
	}
	
	private Thread playMusic() {
		Thread t = new Thread() {
			public void run() {
	//			try {
	//				URL u = getClass().getResource("/other/Osen.mp3");
	//				player = new Player(new BufferedInputStream(u.openStream(),2048));
	//				player.play();
	//				onEndOfPlay();
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
			}
		};
		t.start();
		return t;
	}

	
	private void onEndOfPlay() {
		new Thread() {
			public void run() {
				try {
					th1.join();
					th2.join();
					btnStartPlayer.setEnabled(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	private void doStopPlay() {
		player.stop();
	}

	
	
	
	
	
	
	
	
	
	
	public JTextField getTextFieldCounter() {
		return textFieldCounter;
	}
	public JTextField getTextFieldRefuseCounter() {
		return textFieldRefuseCounter;
	}
	public JLabel getLblCreator1() {
		return lblCreator1;
	}
	public JLabel getLblCreator2() {
		return lblCreator2;
	}
	public JLabel getLblHandler2() {
		return lblHandler2;
	}
}
