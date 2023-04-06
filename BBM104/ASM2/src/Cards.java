import java.util.ArrayList;

public class Cards extends Position{
	
	private int communityChestNo =1;
	private int chanceNo =1;
	
	ListJsonReader chance = new ListJsonReader();
	ListJsonReader communityChest = new ListJsonReader();
	
	
	public String communityChest(Player player,Banker banker, int move) {
		if (position == 3 || position == 18 || position == 34) {
			if (communityChestNo ==1) {
				communityChestNo+=1;
				if (getOldPosition()+ move >40) {
					setBudget(200);
					banker.setBudgetForBanker(-200);
					
				}
				position = 1;
				return " draw Community Chest -advance to go ";
			}
			
			else if (communityChestNo ==2) {
				communityChestNo+=1;
				setBudget(75);
				setBudgetForBanker(-75);
				return " Bank error in your favor - collect $75";
			}
			else if (communityChestNo ==3) {
				communityChestNo+=1;
				if (getBudget()>= 50) {
					setBudget(-50);
					setBudgetForBanker(50);
					return " Doctor's fees - Pay $50";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (communityChestNo ==4) {
				communityChestNo+=1;			
				if (player.getBudget()>=10) {
					setBudget(10);
					player.setBudget(-10);
					return " It is your birthday Collect $10 from each player";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (communityChestNo ==5) {
				communityChestNo+=1;
				if (player.getBudget()>=50) {
					setBudget(50);
					player.setBudget(-50);
					return " Grand Opera Night - collect $50 from every player for opening night seats";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (communityChestNo ==6) {
				communityChestNo+=1;
				setBudget(20);
				setBudgetForBanker(-20);
				return " Income Tax refund - collect $20";
			}
			else if (communityChestNo ==7) {
				communityChestNo+=1;
				setBudget(100);
				setBudgetForBanker(-100);
				return " Life Insurance Matures - collect $100";
			}
			else if (communityChestNo ==8) {
				communityChestNo+=1;
				if (getBudget()>=100) {
					setBudget(-100);
					setBudgetForBanker(100);
					return " Pay Hospital Fees of $100";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (communityChestNo ==9) {
				communityChestNo+=1;
				if (getBudget()>=50) {
					setBudget(-50);
					setBudgetForBanker(50);
					return " Pay School Fees of $50";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (communityChestNo ==10) {
				communityChestNo+=1;
				setBudget(100);
				setBudgetForBanker(-100);
				return " You inherit $100";
				
			}
			else if (communityChestNo ==11) {
				communityChestNo = 0;
				setBudget(50);
				setBudgetForBanker(-50);
				return " From sale of stock you get $50";
				
			}
		}
		return "";
	}
	
	
	public String chance(Banker banker,int move, Player player, Player playerr) {
		if (position == 8 || position == 23 || position == 37) {
			if (chanceNo ==1) {
				chanceNo+=1;
				if (getOldPosition()+ move >40) {
					setBudget(200);
					banker.setBudgetForBanker(-200);
					
				}
				position = 1;
				return " Advance to Go (Collect $200)";
			}
			else if (chanceNo ==2) {
				chanceNo+=1;
				position = 27;
				if (!player.owned.contains("27") && !playerr.owned.contains("27")) {
					return " Advance to Leicester Square "+player.getName()+" bought Leicester Square";
				}
				else if (playerr.owned.contains("27")) {
					return " Advance to Leicester Square "+player.getName()+ " paid rent for Leicester Square";
				}
				else if (player.owned.contains("27")) {
					return " Advance to Leicester Square"+player.getName()+" has Leicester Square";
				}
				return " Advance to Leicester Square";
			}
			else if (chanceNo ==3) {
				chanceNo+=1;
				position -= 3;
				if (position ==5 || position == 39) {
					if (getBudget()>=100) {
						setBudget(-100);
						banker.setBudgetForBanker(100);
						return " Go back 3 spaces "+player.getName()+ " paid Tax";
					}
					else {
						return " goes bankrupt";
					}	
				}
				else if (position == 20) {
					if (!player.owned.contains("20") && !playerr.owned.contains("20")) {
						return " Go back 3 spaces "+player.getName()+" bought Vine Street";
					}
					else if (playerr.owned.contains("20")) {
						return " Go back 3 spaces "+player.getName()+ " paid rent for Vine Street";
					}
					else if (player.owned.contains("20")) {
						return " Go back 3 spaces "+player.getName()+" has Vine Street";
					}
				}
				else if (position == 34) {
					return communityChest(playerr, banker, move);
				}
				
				return " Go back 3 spaces";
			}
			else if (chanceNo ==4) {
				chanceNo+=1;
				if (getBudget()>=15) {
					setBudget(-15);
					banker.setBudgetForBanker(15);
					return " Pay poor tax of $15";
				}
				else {
					return " goes bankrupt";
				}
				
			}
			else if (chanceNo ==5) {
				chanceNo+=1;
				setBudget(150);
				banker.setBudgetForBanker(-150);
				return " Your building loan matures - collect $150";
			}
			else if (chanceNo ==6) {
				chanceNo+=1;
				setBudget(100);
				banker.setBudgetForBanker(-100);
				return " You have won a crossword competition - collect $100 ";
			}
		}
		return "";
	}
}

