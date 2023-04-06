import java.util.ArrayList;

public interface Remove {
	public static void removeJewels(int y, int x, int y1, int x1, int y2, int x2,
			ArrayList<ArrayList<String>> gameGrid) {					// Create a method that can dynamically change the game grid according to the given command.
		gameGrid.get(y).set(x, " ");
		gameGrid.get(y1).set(x1, " ");
		gameGrid.get(y2).set(x2, " ");

		int row = gameGrid.size() - 1;
		for (int i = row; i > 0; i--) {
			int col = gameGrid.get(i).size();
			for (int j = 0; j < col; j++) {
				String coordinate = gameGrid.get(i).get(j);
				String upCoordinate = gameGrid.get(i - 1).get(j);
				String threeUpCoordinate = " ";
				if (i >= 3) {
					threeUpCoordinate = gameGrid.get(i - 3).get(j);
				}
				if (coordinate.equals(" ") && !upCoordinate.equals(" ")) {
					gameGrid.get(i).set(j, upCoordinate);
					gameGrid.get(i - 1).set(j, " ");
				} else if (coordinate.equals(" ") && upCoordinate.equals(" ") && i>=3) {
					gameGrid.get(i).set(j, threeUpCoordinate);
					gameGrid.get(i - 3).set(j, " ");

				}
			}
		}
	}
}
