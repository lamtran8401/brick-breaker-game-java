package mvc;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Controller implements IController {
	private IMapModel model;
	private GameView view;

	public Controller(IMapModel model) {
		this.model = model;
		this.view = new GameView(model, this);
	}

	public void start() {
		view.initialView();
	}

	@Override
	public void update(int row, int col, int val) {
		model.updateMap(row, col, val);
	}

	@Override
	public void setScore(int val) {
		model.setScore(val);
	}

	@Override
	public int getScore() {
		return model.getScore();
	}

	@Override
	public void newMap() {
		this.model.newMap();
	}

	@Override
	public void executeGame() {

		if (view.play) {
			int ballX = view.getBallX();
			int ballY = view.getBallY();

			int ballXdir = view.getBallXdir();
			int ballYdir = view.getBallYdir();

			// ball giao nhau voi thanh yellow bar -> ball chuyen huong y
			if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(view.playerX, 550, 100, 8)))
				view.setBallYdir(-ballYdir);

			A: for (int i = 0; i < model.getRow(); i++) {
				for (int j = 0; j < model.getCol(); j++) {
					if (model.getMap().getDataMap()[i][j] > 0) {
						int brickX = j * model.getBrickWidth() + 80;
						int brickY = i * model.getBrickHeight() + 50;
						int bricksWidth = model.getBrickWidth();
						int bricksHeight = model.getBrickHeight();

						Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
						Rectangle ballrect = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickrect = rect;

						if (ballrect.intersects(brickrect)) {
							update(i, j, 0);
							view.totalBricks--;
							if (ballX + 19 <= brickrect.x || ballX + 1 >= brickrect.x + bricksWidth)
								view.setBallXdir(-ballXdir);
							else
								view.setBallYdir(-view.getBallYdir());

							break A;
						}
					}
				}
			}

			view.setBallX(ballX + view.getBallXdir());
			view.setBallY(ballY + view.getBallYdir());

			if (view.getBallX() < 0)
				view.setBallXdir(-view.getBallXdir());

			if (view.getBallY() < 0)
				view.setBallYdir(-view.getBallYdir());

			if (view.getBallX() > 670)
				view.setBallXdir(-view.getBallXdir());
		}

		model.notifyView();
	}

	@Override
	public void draw(Graphics2D g) {
		model.draw(g);
	}

}
