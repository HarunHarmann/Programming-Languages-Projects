import java.util.ArrayList;

public class Diamond extends Game implements Remove {

	@Override
	public void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel) {

		if (jewel.equals("D")) {

			if (y - 2 >= 0 && x - 2 >= 0 && gameGrid.get(y - 1).get(x - 1).equals("D") &&gameGrid.get(y - 1).get(x - 1).equals(gameGrid.get(y - 2).get(x - 2))) { // Checks for the left diagonals 1		
				Remove.removeJewels(y, x, y - 1, x - 1, y - 2, x - 2, gameGrid);
				addScore("D", "D");
			}
			else if (y + 2 < gameGrid.size() && x + 2 < gameGrid.size() && gameGrid.get(y+1).get(x+1).equals("D") && gameGrid.get(y + 1).get(x + 1).equals(gameGrid.get(y + 2).get(x + 2))) { // 9				
				Remove.removeJewels(y, x, y + 1, x + 1, y + 2, x + 2, gameGrid);
				addScore("D", "D");
			}
			else if (y - 2 >= 0 && x + 2 < gameGrid.size() && gameGrid.get(y - 1).get(x + 1).equals("D") && gameGrid.get(y - 1).get(x + 1).equals(gameGrid.get(y - 2).get(x + 2))) { // Checks for the right diagonals 3		
				Remove.removeJewels(y, x, y - 1, x + 1, y - 2, x + 2, gameGrid);
				addScore("D", "D");
			}else if (y + 2 < gameGrid.size() && x - 2 >= 0 && gameGrid.get(y + 1).get(x - 1).equals("D") && gameGrid.get(y + 1).get(x - 1).equals(gameGrid.get(y + 2).get(x - 2))) { // 7
				Remove.removeJewels(y, x, y + 1, x - 1, y + 2, x - 2, gameGrid);
				addScore("D", "D");				
			}

		}

	}

//	@Override
//	public void removeJewels(int y, int x, int y1, int x1, int y2, int x2, ArrayList<ArrayList<String>> gameGrid) {
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
//	}

	@Override
	public int addScore(String jewel1, String jewel2) {
		setPoint(90);
		return 90;

	}

}
