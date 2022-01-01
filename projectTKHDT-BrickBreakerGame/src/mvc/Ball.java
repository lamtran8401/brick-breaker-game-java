package mvc;

public class Ball {
	private int x;
	private int y;
	private int ballXdir;
	private int ballYdir;

	public Ball() {
		this.x = 120;
		this.y = 350;
		ballXdir = -1;
		ballYdir = -2;
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		ballXdir = -1;
		ballYdir = -2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}

}
