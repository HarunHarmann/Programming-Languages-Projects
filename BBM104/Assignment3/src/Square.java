import java.util.ArrayList;

public class Square extends Game implements Remove {

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {
		if (jewel.equals("S")) {

			if (x - 2 >= 0 && gameGrid.get(y).get(x - 1).equals("S")
					&& gameGrid.get(y).get(x - 1).equals(gameGrid.get(y).get(x - 2))) { // Checks for the left
																						// horizontal.
				Remove.removeJewels(y, x, y, x - 1, y, x - 2, gameGrid);
				addScore("S", "S");
			} else if (x + 2 < gameGrid.size() && gameGrid.get(y).get(x + 1).equals("S")
					&& gameGrid.get(y).get(x + 1).equals(gameGrid.get(y).get(x + 2))) { // Checks for the right
																						// horizontal
				Remove.removeJewels(y, x, y, x + 1, y, x + 2, gameGrid);
				addScore("S", "S");
			}

		}

	}

//	@Override
//	public void removeJewels() {
//		gameGrid.get(y).set(x, " ");		
//		gameGrid.get(y1).set(x1, " ");		
//		gameGrid.get(y2).set(x2, " ");
//		
//		int row = gameGrid.size();
//		for (int i = 0; i < row; i++) {
//		    int col = gameGrid.get(i).size();
//		    for (int j = col; j >0; j++) {
//		    	String coordinate = gameGrid.get(i).get(j);
//		    	String upCoordinate = gameGrid.get(i).get(j-1);  	
//		    	if ( coordinate.equals(" ")) {
//					gameGrid.get(i).set(j, upCoordinate);
//				}
//		    }
//		}
//		
//		
//	}

	@Override
	public int addScore(String jewel1, String jewel2) {
		setPoint(45);
		return 45;

	}

}
