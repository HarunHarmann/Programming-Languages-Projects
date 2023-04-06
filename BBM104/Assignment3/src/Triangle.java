import java.util.ArrayList;

public class Triangle extends Game implements Remove{

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {
		if (jewel.equals("T")) {

			if (y - 2 >= 0 && gameGrid.get(y - 1).get(x).equals("T")
					&& gameGrid.get(y - 1).get(x).equals(gameGrid.get(y - 2).get(x))) { // Checks for the up vertical.
				Remove.removeJewels(y, x, y - 1, x, y - 2, x, gameGrid);
				addScore("T", "T");
			} else if (y + 2 < gameGrid.size() && gameGrid.get(y + 1).get(x).equals("T")
					&& gameGrid.get(y + 1).get(x).equals(gameGrid.get(y + 2).get(x))) { // Checks for the down vertical.
				Remove.removeJewels(y, x, y + 1, x, y + 2, x, gameGrid);
				addScore("T", "T");
			}

		}

	}

	@Override
	public int addScore(String jewel1, String jewel2) {
		setPoint(45);
		return 45;

	}

}
