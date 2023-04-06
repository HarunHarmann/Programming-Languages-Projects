import java.util.ArrayList;

public abstract class Game extends Jewels{
	public abstract void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid, String jewel);  // Create an abstract class to check the coordinates next to chosen coordinate for jewels.
	public abstract int addScore(String jewel1, String jewel2);  // If any association found, add score.

}
