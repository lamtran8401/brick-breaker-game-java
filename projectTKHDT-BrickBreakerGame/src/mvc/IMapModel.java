package mvc;

import java.awt.Graphics2D;

public interface IMapModel {

	void initilize();

	DataMap getMap();

	void newMap();

	void draw(Graphics2D g);

	int getScore();

	void setScore(int val);

	void updateScore();

	int getRow();

	int getCol();

	int getBrickWidth();

	int getBrickHeight();

	void register(Observer observer);

	void remove(Observer observer);

	void notify(int row, int col, int val);
}
