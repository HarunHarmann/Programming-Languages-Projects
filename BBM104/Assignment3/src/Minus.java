import java.util.ArrayList;

public class Minus extends Jewels implements MathSigns, Remove{

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {
		if (jewel.equals("-")) {
			if (x - 2 >= 0 && operators.contains(gameGrid.get(y).get(x - 1))
					&& operators.contains(gameGrid.get(y).get(x - 2))) { // Checks for the left
																						// horizontal.
				Remove.removeJewels(y, x, y, x - 1, y, x - 2, gameGrid);
				setPoint(MathSigns.addScore());
			} else if (x + 2 < gameGrid.size() && operators.contains(gameGrid.get(y).get(x + 1))
					&& operators.contains(gameGrid.get(y).get(x + 2))) { // Checks for the right
																						// horizontal
				Remove.removeJewels(y, x, y, x + 1, y, x + 2, gameGrid);
				setPoint(MathSigns.addScore());
			}
		}
		
	}


}
