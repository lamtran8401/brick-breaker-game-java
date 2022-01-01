package mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameView extends JPanel implements KeyListener, ActionListener, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMapModel model;
	private IController controller;

	private JFrame frame = new JFrame();

	public int totalBricks;
	private Timer Timer;
	private int delay = 8;

	boolean play = false;
	int playerX = 310; // yellow bar x position
	Random rd = new Random();

	private Ball ball;

	public GameView(IMapModel model, IController controller) {
		this.model = model;
		this.controller = controller;

		model.register(this);
		int ballX = rd.nextInt(550) + 50;
		int ballY = rd.nextInt(50) + 400;
		this.ball = new Ball(ballX, ballY);
		setTotalBricks();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		Timer = new Timer(delay, this);
		Timer.start();
	}

	public void setTotalBricks() {
		totalBricks = model.getRow() * model.getCol();
	}

	public void initialView() {
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Brick Breaker Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		controller.draw((Graphics2D) g);

		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + controller.getScore(), 590, 30);

		g.setColor(Color.yellow);
		g.fillRect(playerX, 550, 100, 8);

		// ball
		g.setColor(Color.GREEN);
		g.fillOval(getBallX(), getBallY(), 20, 20);

		// neu roi khoi bar => gameover
		if (getBallY() > 570) {
			play = false;
			setBallXdir(0);
			setBallYdir(0);
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("    Game Over Score: " + controller.getScore(), 190, 300);

			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("   Press Enter to Restart", 190, 340);
		}
		if (totalBricks == 0) {
			play = false;
			setBallYdir(-2);
			setBallXdir(-1);
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("    Winner: " + model.getScore(), 190, 300);

			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("   Press Enter to Restart", 190, 340);
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Timer.start();

		if (play)
			controller.executeGame();

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600)
				playerX = 600;
			else
				moveRight();

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10)
				playerX = 10;
			else
				moveLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				restart();
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void update(int row, int col, int val) {
		controller.update(row, col, val);
	}

	public void restart() {
		// auto create new ball's position: x from 50 -> 599, y from 400 -> 449
		int ballX = rd.nextInt(550) + 50;
		int ballY = rd.nextInt(50) + 400;
		setBallX(ballX);
		setBallY(ballY);
		setBallXdir(-1);
		setBallYdir(-2);
		playerX = 310;
		controller.setScore(0);
		controller.newMap();
		setTotalBricks();
	}

	// method for yellow bar [player]
	public void moveRight() {
		play = true;
		playerX += 20;
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	// method for ball
	public int getBallX() {
		return ball.getX();
	}

	public int getBallY() {
		return ball.getY();
	}

	public void setBallX(int val) {
		ball.setX(val);
	}

	public void setBallY(int val) {
		ball.setY(val);
	}

	public int getBallXdir() {
		return ball.getBallXdir();
	}

	public int getBallYdir() {
		return ball.getBallYdir();
	}

	public void setBallXdir(int val) {
		ball.setBallXdir(val);
	}

	public void setBallYdir(int val) {
		ball.setBallYdir(val);
	}

}
