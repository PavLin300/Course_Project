package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Queue;

import javax.swing.JFrame;

import models.Counter;
import models.Creator;
import models.Handler;


import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private Image backgroundImage;
	

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
		frame.setBounds(100, 100, 900, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Завантажуємо зображення фону
        backgroundImage = new ImageIcon(getClass().getResource("/photo/background.jpg")).getImage(); // Вказати правильний шлях до вашого JPG
        
        // Панель, на якій малюється фон
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Масштабуємо зображення до розміру панелі
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setBounds(0, 0, 884, 560);
        frame.getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

		
		btnStartPlayer = new JButton("New button");
		btnStartPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doRun();
			}
		});
		btnStartPlayer.setBounds(619, 490, 230, 46);
		contentPanel.add(btnStartPlayer);
		
		textFieldCounter = new JTextField();
		textFieldCounter.setText("Counter");
		textFieldCounter.setBounds(635, 129, 102, 28);
		contentPanel.add(textFieldCounter);
		textFieldCounter.setColumns(10);
		
		textFieldRefuseCounter = new JTextField();
		textFieldRefuseCounter.setText("RefuseCounter");
		textFieldRefuseCounter.setColumns(10);
		textFieldRefuseCounter.setBounds(712, 433, 102, 31);
		contentPanel.add(textFieldRefuseCounter);
		
		lblCreator1 = new JLabel("");
		lblCreator1.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblCreator1.setBounds(198, 296, 102, 125);
		contentPanel.add(lblCreator1);
		
		lblCreator2 = new JLabel("");
		lblCreator2.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblCreator2.setBounds(357, 358, 102, 125);
		contentPanel.add(lblCreator2);
		
		lblHandler1 = new JLabel("");
		lblHandler1.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblHandler1.setBounds(449, 213, 102, 134);
		contentPanel.add(lblHandler1);
		
		lblHandler2 = new JLabel("");
		lblHandler2.setIcon(new ImageIcon(MainGui.class.getResource("/photo/man2.png")));
		lblHandler2.setBounds(635, 259, 102, 134);
		contentPanel.add(lblHandler2);
		
		steptimeSlider = new JSlider();
		steptimeSlider.setMinorTickSpacing(10);
		steptimeSlider.setMajorTickSpacing(20);
		steptimeSlider.setPaintTicks(true);
		steptimeSlider.setPaintLabels(true);
		steptimeSlider.setMinimum(20);
		steptimeSlider.setBounds(22, 490, 200, 37);
		steptimeSlider.setOpaque(false);
		steptimeSlider.setBackground(new Color(0, 0, 0, 0));
		contentPanel.add(steptimeSlider);
		
		queueSlider = new JSlider();
		queueSlider.setValue(0);
		queueSlider.setMaximum(10);
		queueSlider.setMajorTickSpacing(2);
		queueSlider.setPaintLabels(true);
		queueSlider.setPaintTicks(true);
		queueSlider.setOrientation(SwingConstants.VERTICAL);
		queueSlider.setBounds(472, 360, 117, 119);
		queueSlider.setOpaque(false);
		queueSlider.setBackground(new Color(0, 0, 0, 0));
		contentPanel.add(queueSlider);
		
		minCreateTimeSlider = new JSlider();
		minCreateTimeSlider.setMajorTickSpacing(250);
		minCreateTimeSlider.setMinorTickSpacing(25);
		minCreateTimeSlider.setPaintTicks(true);
		minCreateTimeSlider.setPaintLabels(true);
		minCreateTimeSlider.setMinimum(250);
		minCreateTimeSlider.setMaximum(1000);
		minCreateTimeSlider.setBounds(10, 26, 200, 46);
		minCreateTimeSlider.setOpaque(false);
		minCreateTimeSlider.setBackground(new Color(0, 0, 0, 0));
		contentPanel.add(minCreateTimeSlider);
		
		minHandlTimeSlider = new JSlider();
		minHandlTimeSlider.setPaintLabels(true);
		minHandlTimeSlider.setPaintTicks(true);
		minHandlTimeSlider.setMinimum(250);
		minHandlTimeSlider.setMajorTickSpacing(250);
		minHandlTimeSlider.setMinorTickSpacing(25);
		minHandlTimeSlider.setMaximum(1000);
		minHandlTimeSlider.setBounds(274, 26, 230, 46);
		minHandlTimeSlider.setOpaque(false);
		minHandlTimeSlider.setBackground(new Color(0, 0, 0, 0));
		contentPanel.add(minHandlTimeSlider);
		
		JLabel lblNewLabel = new JLabel("Create time");
		lblNewLabel.setBounds(71, 11, 117, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblHandleTime = new JLabel("Handle time");
		lblHandleTime.setBounds(374, 11, 117, 14);
		contentPanel.add(lblHandleTime);
		
		JLabel lblNewLabel_1 = new JLabel("Speed");
		lblNewLabel_1.setBounds(83, 465, 36, 14);
		contentPanel.add(lblNewLabel_1);
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
