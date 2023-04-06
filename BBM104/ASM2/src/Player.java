

public class Player extends Property{
	
	private String name;
	private int cost;
	public String[] board = new String[40];
	
	
	public Player() {
		
		setBoard();
		setCost(15000);		
	}
		
	public void setBoard() {               // Game board is created.
		for (int i = 1; i <= 40; i++) {
			board[i-1] = String.valueOf(i);
		}
	}


	public void setBoard(String[] board) {
		this.board = board;
	}

	

	public void setPosition(int position) {
		this.position = position;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String GO(Banker banker,int move) {
		
		if (getOldPosition()+ move >40 && position!=1) {
			setBudget(200);
			banker.setBudgetForBanker(-200);
			
		}
		else if (position ==1) {
			
			setBudget(200);
			banker.setBudgetForBanker(-200);
			return " is in GO square";
		}
		
		
		
		return "";
		
	}
	
	public String payTax(Banker banker) {
		if (position ==5 || position == 39) {
			if (getBudget()>=100) {
				setBudget(-100);
				banker.setBudgetForBanker(100);
				return " paid Tax";
			}
			else {
				return " goes bankrupt";
			}		
		}
		return "";
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}