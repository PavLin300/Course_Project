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
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

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
		frame.setBounds(100, 100, 645, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnStartPlayer = new JButton("New button");
		btnStartPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doRun();
			}
		});
		btnStartPlayer.setBounds(389, 391, 230, 46);
		frame.getContentPane().add(btnStartPlayer);
		
		textFieldCounter = new JTextField();
		textFieldCounter.setText("Counter");
		textFieldCounter.setBounds(505, 103, 102, 28);
		frame.getContentPane().add(textFieldCounter);
		textFieldCounter.setColumns(10);
		
		textFieldRefuseCounter = new JTextField();
		textFieldRefuseCounter.setText("RefuseCounter");
		textFieldRefuseCounter.setColumns(10);
		textFieldRefuseCounter.setBounds(505, 233, 102, 31);
		frame.getContentPane().add(textFieldRefuseCounter);
		
		lblCreator1 = new JLabel("");
		lblCreator1.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblCreator1.setBounds(10, 71, 102, 125);
		frame.getContentPane().add(lblCreator1);
		
		lblCreator2 = new JLabel("");
		lblCreator2.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblCreator2.setBounds(10, 242, 102, 125);
		frame.getContentPane().add(lblCreator2);
		
		lblHandler1 = new JLabel("");
		lblHandler1.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblHandler1.setBounds(344, 71, 102, 134);
		frame.getContentPane().add(lblHandler1);
		
		lblHandler2 = new JLabel("");
		lblHandler2.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblHandler2.setBounds(344, 233, 102, 134);
		frame.getContentPane().add(lblHandler2);
		
		steptimeSlider = new JSlider();
		steptimeSlider.setMinorTickSpacing(10);
		steptimeSlider.setMajorTickSpacing(20);
		steptimeSlider.setPaintTicks(true);
		steptimeSlider.setPaintLabels(true);
		steptimeSlider.setMinimum(20);
		steptimeSlider.setBounds(20, 400, 200, 37);
		frame.getContentPane().add(steptimeSlider);
		
		queueSlider = new JSlider();
		queueSlider.setValue(0);
		queueSlider.setMaximum(10);
		queueSlider.setMajorTickSpacing(2);
		queueSlider.setPaintLabels(true);
		queueSlider.setPaintTicks(true);
		queueSlider.setOrientation(SwingConstants.VERTICAL);
		queueSlider.setBounds(158, 129, 117, 119);
		frame.getContentPane().add(queueSlider);
		
		minCreateTimeSlider = new JSlider();
		minCreateTimeSlider.setMajorTickSpacing(250);
		minCreateTimeSlider.setMinorTickSpacing(25);
		minCreateTimeSlider.setPaintTicks(true);
		minCreateTimeSlider.setPaintLabels(true);
		minCreateTimeSlider.setMinimum(250);
		minCreateTimeSlider.setMaximum(1000);
		minCreateTimeSlider.setBounds(10, 26, 200, 46);
		frame.getContentPane().add(minCreateTimeSlider);
		
		minHandlTimeSlider = new JSlider();
		minHandlTimeSlider.setPaintLabels(true);
		minHandlTimeSlider.setPaintTicks(true);
		minHandlTimeSlider.setMinimum(250);
		minHandlTimeSlider.setMajorTickSpacing(250);
		minHandlTimeSlider.setMinorTickSpacing(25);
		minHandlTimeSlider.setMaximum(1000);
		minHandlTimeSlider.setBounds(274, 26, 230, 46);
		frame.getContentPane().add(minHandlTimeSlider);
		
		JLabel lblNewLabel = new JLabel("Create time");
		lblNewLabel.setBounds(71, 11, 117, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblHandleTime = new JLabel("Handle time");
		lblHandleTime.setBounds(374, 11, 117, 14);
		frame.getContentPane().add(lblHandleTime);
		
		JLabel lblNewLabel_1 = new JLabel("Speed");
		lblNewLabel_1.setBounds(84, 375, 36, 14);
		frame.getContentPane().add(lblNewLabel_1);
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
