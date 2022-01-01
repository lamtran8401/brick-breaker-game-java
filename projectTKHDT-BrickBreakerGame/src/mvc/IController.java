package mvc;

import java.awt.Graphics2D;

public interface IController {

	void start();

	void update(int row, int col, int val);

	void setScore(int val);

	int getScore();

	void newMap();

	void executeGame();

	void draw(Graphics2D g);
}
