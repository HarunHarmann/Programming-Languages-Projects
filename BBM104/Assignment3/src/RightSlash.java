import java.util.ArrayList;

public class RightSlash extends Jewels implements MathSigns, Remove {

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid,String jewel) {
		if (jewel.equals("/")) {
			if (y - 2 >= 0 && x + 2 < gameGrid.size() && operators.contains(gameGrid.get(y-1).get(x+1)) && operators.contains(gameGrid.get(y-2).get(x+2))) { // Checks for the right diagonals 3		
				Remove.removeJewels(y, x, y - 1, x + 1, y - 2, x + 2, gameGrid);
				setPoint(MathSigns.addScore());
			}else if (y + 2 < gameGrid.size() && x - 2 >= 0 && operators.contains(gameGrid.get(y + 1).get(x - 1)) && operators.contains(gameGrid.get(y + 2).get(x - 2))) { // 7
				Remove.removeJewels(y, x, y + 1, x - 1, y + 2, x - 2, gameGrid);
				setPoint(MathSigns.addScore());			
			}
	
		}
	}
}
