import java.util.ArrayList;

public class Stick  extends Jewels implements MathSigns, Remove {

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {
		if (y - 2 >= 0 && operators.contains(gameGrid.get(y - 1).get(x))
				&& operators.contains(gameGrid.get(y - 2).get(x))) { // Checks for the up vertical.
			Remove.removeJewels(y, x, y - 1, x, y - 2, x, gameGrid);
			setPoint(MathSigns.addScore());
		} else if (y + 2 < gameGrid.size() && operators.contains(gameGrid.get(y + 1).get(x))
				&& operators.contains(gameGrid.get(y + 2).get(x))) { // Checks for the down vertical.
			Remove.removeJewels(y, x, y + 1, x, y + 2, x, gameGrid);
			setPoint(MathSigns.addScore());
		}

	}

}
