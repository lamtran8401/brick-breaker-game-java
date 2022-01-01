package mvc;

public class DataMap {
	private int[][] datamap;

	public DataMap() {
		this.datamap = new int[3][7];
	}

	public int[][] getDataMap() {
		return datamap;
	}

	public void initialMap() {
		for (int i = 0; i < datamap.length; i++) {
			for (int j = 0; j < datamap[0].length; j++) {
				datamap[i][j] = 1;
			}
		}
	}

	public void updateMap(int row, int col, int val) {
		datamap[row][col] = val;
	}

	public int getRow() {
		return datamap.length;
	}

	public int getCol() {
		return datamap[0].length;
	}
}
