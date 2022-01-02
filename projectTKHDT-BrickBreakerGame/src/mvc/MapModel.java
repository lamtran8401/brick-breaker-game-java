package mvc;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.BasicStroke;
import java.awt.Color;

public class MapModel implements IMapModel {
	List<Observer> observers = new ArrayList<>();

	private DataMap dataMap;
	private int bricksWidth;
	private int bricksHeight;
	private int score = 0;

	public MapModel() {
		this.dataMap = new DataMap();
		initilize();
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < dataMap.getRow(); i++) {

			for (int j = 0; j < dataMap.getCol(); j++) {

				if (dataMap.getDataMap()[i][j] > 0) {
					// ve cuc gach
					g.setColor(Color.red);
					g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

					// ve vien den
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
				}
			}
		}
	}

	public void setBricksValue(int row, int col, int val) {
		dataMap.updateMap(row, col, val);
	}

	// for observable
	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyView() {
		observers.forEach(observer -> observer.update());
	}

	// for update data model
	@Override
	public void initilize() {
		dataMap.initialMap();
		bricksWidth = 540 / dataMap.getCol();
		bricksHeight = 150 / dataMap.getRow();

	}

	@Override
	public void newMap() {
		dataMap = new DataMap();
		initilize();
	}

	@Override
	public void updateMap(int row, int col, int val) {
		setBricksValue(row, col, val);
		updateScore();
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void updateScore() {
		score += 5;
	}

	@Override
	public DataMap getMap() {
		return dataMap;
	}

	@Override
	public int getRow() {
		return dataMap.getRow();
	}

	@Override
	public int getCol() {
		return dataMap.getCol();
	}

	@Override
	public void setScore(int val) {
		score = val;

	}

	@Override
	public int getBrickWidth() {
		return bricksWidth;
	}

	@Override
	public int getBrickHeight() {
		return bricksHeight;
	}

}
