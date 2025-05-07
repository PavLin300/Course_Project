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
import javax.swing.JSlider;

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
	private JSlider steptimeSlider;
	private JSlider queueSlider;
	private JSlider minCreateTimeSlider;
	private JSlider minHandlTimeSlider;

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
		frame.setBounds(100, 100, 576, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnStartPlayer = new JButton("New button");
		btnStartPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doRun();
			}
		});
		btnStartPlayer.setBounds(174, 333, 230, 46);
		frame.getContentPane().add(btnStartPlayer);
		
		textFieldCounter = new JTextField();
		textFieldCounter.setBounds(464, 36, 86, 20);
		frame.getContentPane().add(textFieldCounter);
		textFieldCounter.setColumns(10);
		
		textFieldRefuseCounter = new JTextField();
		textFieldRefuseCounter.setColumns(10);
		textFieldRefuseCounter.setBounds(474, 71, 86, 20);
		frame.getContentPane().add(textFieldRefuseCounter);
		
		lblCreator1 = new JLabel("creator 1");
		lblCreator1.setBounds(35, 74, 71, 52);
		frame.getContentPane().add(lblCreator1);
		
		lblCreator2 = new JLabel("creator 2");
		lblCreator2.setBounds(39, 220, 59, 64);
		frame.getContentPane().add(lblCreator2);
		
		lblHandler1 = new JLabel("handler 1");
		lblHandler1.setBounds(140, 93, 61, 69);
		frame.getContentPane().add(lblHandler1);
		
		lblHandler2 = new JLabel("handler 2");
		lblHandler2.setBounds(140, 226, 86, 52);
		frame.getContentPane().add(lblHandler2);
		
		steptimeSlider = new JSlider();
		steptimeSlider.setBounds(234, 50, 200, 26);
		frame.getContentPane().add(steptimeSlider);
		
		queueSlider = new JSlider();
		queueSlider.setBounds(234, 11, 200, 26);
		frame.getContentPane().add(queueSlider);
		
		minCreateTimeSlider = new JSlider();
		minCreateTimeSlider.setBounds(234, 112, 200, 26);
		frame.getContentPane().add(minCreateTimeSlider);
		
		minHandlTimeSlider = new JSlider();
		minHandlTimeSlider.setBounds(234, 159, 200, 26);
		frame.getContentPane().add(minHandlTimeSlider);
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
	
	public JSlider getStepTimeSlider() {
		return steptimeSlider;
	}
	
	
	protected void doRun() {
		btnStartPlayer.setEnabled(false);
		Counter counter = new Counter(textFieldCounter);
		Counter refuseCounter = new Counter(textFieldRefuseCounter);
		models.Queue queue = new models.Queue(this, refuseCounter, queueSlider);
		Creator creator1 = new Creator(this, lblCreator1, queue, minCreateTimeSlider);
		Creator creator2 = new Creator(this, lblCreator2, queue, minCreateTimeSlider);
		Handler handler1 = new Handler(this, lblHandler1, queue, minHandlTimeSlider, counter);
		Handler handler2 = new Handler(this, lblHandler2, queue, minHandlTimeSlider, counter);
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
	public JSlider getSteptimeSlider() {
		return steptimeSlider;
	}
	public JSlider getQueueSlider() {
		return queueSlider;
	}
}
