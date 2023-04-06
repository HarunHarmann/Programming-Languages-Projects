import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MathSigns {
	List<String> operators = new ArrayList<>(Arrays.asList("/","+","-","|","\\")); 
	public abstract void findKind(int y, int x, ArrayList<ArrayList<String>> gameGrid,String jewel); // Create an abstract class to check the coordinates next to chosen coordinate for operators.
	public static int addScore() {  // Create an static addScore method for any kind of operator.
		return 60;
	}
}
