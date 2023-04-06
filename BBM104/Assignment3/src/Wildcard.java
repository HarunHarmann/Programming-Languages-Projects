import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wildcard extends Game implements Remove {
	List<String> operators = new ArrayList<>(Arrays.asList("/", "+", "-", "|", "\\"));

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {

		if (jewel.equals("W")) {
			String jewel1;
			String jewel2;
			if (y - 2 >= 0 && gameGrid.get(y - 1).get(x).equals(gameGrid.get(y - 2).get(x))
					|| y - 2 >= 0 && gameGrid.get(y - 2).get(x).equals("W")
					|| y - 2 >= 0 && gameGrid.get(y - 1).get(x).equals("W")) { // Checks for the up vertical.

				jewel1 = gameGrid.get(y - 1).get(x);
				jewel2 = gameGrid.get(y - 2).get(x);
				Remove.removeJewels(y, x, y - 1, x, y - 2, x, gameGrid);
				addScore(jewel1, jewel2);
			} else if (y + 2 < gameGrid.size() && gameGrid.get(y + 1).get(x).equals(gameGrid.get(y + 2).get(x))
					|| y + 2 < gameGrid.size() && gameGrid.get(y + 2).get(x).equals("W")
					|| (y + 2 < gameGrid.size() && gameGrid.get(y + 1).get(x).equals("W"))) { // Checks for the down
																								// vertical.

				jewel1 = gameGrid.get(y + 1).get(x);
				jewel2 = gameGrid.get(y + 2).get(x);
				Remove.removeJewels(y, x, y + 1, x, y + 2, x, gameGrid);
				addScore(jewel1, jewel2);
			}

			else if (x - 2 >= 0 && gameGrid.get(y).get(x - 1).equals(gameGrid.get(y).get(x - 2))
					|| x - 2 >= 0 && gameGrid.get(y).get(x - 2).equals("W")
					|| x - 2 >= 0 && gameGrid.get(y).get(x - 1).equals("W")) { // Checks for the left horizontal

				jewel1 = gameGrid.get(y).get(x - 1);
				jewel2 = gameGrid.get(y).get(x - 2);
				Remove.removeJewels(y, x, y, x - 1, y, x - 2, gameGrid);
				addScore(jewel1, jewel2);
			} else if (x + 2 < gameGrid.size() && gameGrid.get(y).get(x + 1).equals(gameGrid.get(y).get(x + 2))
					|| x + 2 < gameGrid.size() && gameGrid.get(y).get(x + 2).equals("W")
					|| x + 2 < gameGrid.size() && gameGrid.get(y).get(x + 1).equals("W")) { // Checks for the right
																							// horizontal

				jewel1 = gameGrid.get(y).get(x + 1);
				jewel2 = gameGrid.get(y).get(x + 2);
				Remove.removeJewels(y, x, y, x + 1, y, x + 2, gameGrid);
				addScore(jewel1, jewel2);
			}

			else if (y - 2 >= 0 && x - 2 >= 0 && gameGrid.get(y - 1).get(x - 1).equals(gameGrid.get(y - 2).get(x - 2))
					|| y - 2 >= 0 && x - 2 >= 0 && gameGrid.get(y - 1).get(x - 1).equals("W")
					|| y - 2 >= 0 && x - 2 >= 0 && gameGrid.get(y - 2).get(x - 2).equals("W")) { // Checks for the left
																									// diagonals 1

				jewel1 = gameGrid.get(y - 1).get(x - 1);
				jewel2 = gameGrid.get(y - 2).get(x - 2);
				Remove.removeJewels(y, x, y - 1, x - 1, y - 2, x - 2, gameGrid);
				addScore(jewel1, jewel2);
			} else if (y + 2 < gameGrid.size() && x + 2 < gameGrid.size()
					&& gameGrid.get(y + 1).get(x + 1).equals(gameGrid.get(y + 2).get(x + 2))
					|| y + 2 < gameGrid.size() && x + 2 < gameGrid.size() && gameGrid.get(y + 1).get(x + 1).equals("W")
					|| y + 2 < gameGrid.size() && x + 2 < gameGrid.size()
							&& gameGrid.get(y + 2).get(x + 2).equals("W")) { // 9

				jewel1 = gameGrid.get(y + 1).get(x + 1);
				jewel2 = gameGrid.get(y + 2).get(x + 2);
				Remove.removeJewels(y, x, y + 1, x + 1, y + 2, x + 2, gameGrid);
				addScore(jewel1, jewel2);
			} else if (y - 2 >= 0 && x + 2 < gameGrid.size()
					&& gameGrid.get(y - 1).get(x + 1).equals(gameGrid.get(y - 2).get(x + 2))
					|| y - 2 >= 0 && x + 2 < gameGrid.size() && gameGrid.get(y - 1).get(x + 1).equals("W")
					|| y - 2 >= 0 && x + 2 < gameGrid.size() && gameGrid.get(y - 2).get(x + 2).equals("W")) { // Checks
																												// for
																												// the
																												// right
																												// diagonals
																												// 3

				jewel1 = gameGrid.get(y - 1).get(x + 1);
				jewel2 = gameGrid.get(y - 2).get(x + 2);
				Remove.removeJewels(y, x, y - 1, x + 1, y - 2, x + 2, gameGrid);
				addScore(jewel1, jewel2);
			} else if (y + 2 < gameGrid.size() && x - 2 >= 0
					&& gameGrid.get(y + 1).get(x - 1).equals(gameGrid.get(y + 2).get(x - 2))
					|| y + 2 < gameGrid.size() && x - 2 >= 0 && gameGrid.get(y + 1).get(x - 1).equals("W")
					|| y + 2 < gameGrid.size() && x - 2 >= 0 && gameGrid.get(y + 2).get(x - 2).equals("W")) { // 7

				jewel1 = gameGrid.get(y + 1).get(x - 1);
				jewel2 = gameGrid.get(y + 2).get(x - 2);
				Remove.removeJewels(y, x, y + 1, x - 1, y + 2, x - 2, gameGrid);
				addScore(jewel1, jewel2);
			}
		}

	}

	@Override
	public int addScore(String jewel1, String jewel2) {
		if (jewel1.equals("D") && jewel2.equals("D")) { // Scoring for W+D
			setPoint(70);
			return 70;
		} else if (jewel1.equals("D") && jewel2.equals("W") || jewel1.equals("W") && jewel2.equals("D")) {
			setPoint(50);
			return 50;
		} else if (jewel1.equals("W") && jewel2.equals("W")) { // Scoring for three W
			setPoint(30);
			return 30;
		} else if (jewel1.equals("S") && jewel2.equals("S")) { // Scoring for W+S
			setPoint(40);
			return 40;
		} else if (jewel1.equals("S") && jewel2.equals("W") || jewel1.equals("W") && jewel2.equals("S")) {
			setPoint(35);
			return 35;
		} else if (jewel1.equals("T") && jewel2.equals("T")) { // Scoring for W+T
			setPoint(40);
			return 40;
		} else if (jewel1.equals("T") && jewel2.equals("W") || jewel1.equals("W") && jewel2.equals("T")) {
			setPoint(35);
			return 35;
		}
		return 0;

	}

}
