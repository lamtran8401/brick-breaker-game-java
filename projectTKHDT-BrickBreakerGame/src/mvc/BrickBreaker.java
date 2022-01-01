package mvc;

public class BrickBreaker {

	public static void main(String[] args) {
		IMapModel model = new MapModel();
		IController controller = new Controller(model);
		controller.start();
	}
}
