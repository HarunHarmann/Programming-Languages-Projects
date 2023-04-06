
public class Money {
	private int budget = 15000;
	private int budgetForBanker =100000;
	
	
	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int money) {
		this.budget += money;
	}

	public int getBudgetForBanker() {
		return budgetForBanker;
	}

	public void setBudgetForBanker(int budgetForBanker) {
		this.budgetForBanker += budgetForBanker;
	}

	
}

