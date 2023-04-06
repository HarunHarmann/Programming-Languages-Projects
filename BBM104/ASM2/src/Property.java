import java.util.ArrayList;


public  class Property extends Cards{
	
	public String[] board = new String[40];
	
	public ArrayList<String> owned = new ArrayList<>();
	public ArrayList<String> ownedName = new ArrayList<>();
	private ArrayList<String>landList = new ArrayList<>();
	private ArrayList<String>companyList = new ArrayList<>();
	private ArrayList<String>railRoadList = new ArrayList<>();
	private ArrayList<String>railRoad;
	private ArrayList<String>land;
	private ArrayList<String>company;
	
	private ArrayList<String>purchasedlandList =new ArrayList<>();
	private ArrayList<String>purchasedcompanyList =new ArrayList<>();
	private ArrayList<String>purchasedRailRoadList =new ArrayList<>();
	
	PropertyJsonReader lands = new PropertyJsonReader();
	PropertyJsonReader companies = new PropertyJsonReader();
	PropertyJsonReader railRoads = new PropertyJsonReader();
	
	public Property() {
		for (String s : lands.getLands()) {
			landList.add(s);
		}
		for (String s : companies.getCompanies()) {
			companyList.add(s);
		}
		for (String s : railRoads.getRailRoads()) {
			railRoadList.add(s);
		}
		land = lands.getLands();
		railRoad = railRoads.getRailRoads();
		company = companies.getCompanies();
		
	}
	
	public String land(Banker banker, Player player) {
		
		for (int i = 1; i <= 40; i++) {
		board[i-1] = String.valueOf(i);
	}
		
		String landID =board[position-1]; 
		String landName = land.get(land.indexOf(landID)+1); 
		String landCost= land.get(land.indexOf(landID)+2);	
		
		if (player.owned.contains(landID) && land.contains(landID)) {				// Statement for rent.Checks for whether the other player purchased that place.
			
			if (0<=Integer.parseInt(landCost) && Integer.parseInt(landCost)<=2000) {
				int payment =(int) ((0.4)*Integer.parseInt(landCost));
				if (getBudget()>= payment) {
					setBudget(-payment);			
					player.setBudget(payment);
					return " paid rent for "+landName;
				}
				else {
					return " goes bankrupt";
				}
				
			
			}
			
			else if (2001<=Integer.parseInt(landCost) && Integer.parseInt(landCost)<=3000) {
				int payment =3*Integer.parseInt(landCost) /10;
				if (getBudget()>= payment) {
					setBudget(-payment);
					player.setBudget(payment);
					return " paid rent for "+landName;
				}
				else {
					return " goes bankrupt";
				}
				
			}
			
			else if (3001<=Integer.parseInt(landCost) && Integer.parseInt(landCost)<=4000) {
				int payment =(35/100)*Integer.parseInt(landCost);
				if (getBudget()>= payment) {
					setBudget(-payment);
					player.setBudget(payment);
					return " paid rent for "+landName;
				}
				else {
					return " goes bankrupt";
				}
			}
			return "";
		}
		
		else if (landList.contains(landID)) {		// Purchase process for land.
			if (getBudget()>= Integer.parseInt(landCost)) {	
				owned.add(landID);
				owned.add(landName);
				owned.add(landCost);
				
				ownedName.add(landName);
				
				landList.remove(landID);						// These commands will remove the land that is sold.
				landList.remove(landName);
				landList.remove(landCost);
				
				purchasedlandList.add(landID);					// This list will keep the number of the lands purchased by player.
				
				setBudget(-Integer.parseInt(landCost));
				banker.setBudgetForBanker(Integer.parseInt(landCost));
				return " bought "+ landName;
			}
			else {
				return " goes bankrupt";
			}
			
		}
		else if (purchasedlandList.contains(landID)) {
			landName = owned.get(owned.indexOf(landID)+1);
			return " has "+landName;
		}
		
		return "";
	}
	
	public String railRoad(Banker banker, Player player) {
		
		for (int i = 1; i <= 40; i++) {
		board[i-1] = String.valueOf(i);
	}
		
		String railRoadID =board[position-1]; 
		String railRoadName = railRoad.get(railRoad.indexOf(railRoadID)+1); 
		String railRoadCost= railRoad.get(railRoad.indexOf(railRoadID)+2);
		
		if (player.owned.contains(railRoadID) && railRoad.contains(railRoadID)) {				// Statement for rent.Checks for whether the other player purchased that place.
			if (getBudget()>=(25)* player.getPurchasedRailRoadList().size()) {
				setBudget((-25)* player.getPurchasedRailRoadList().size());
				player.setBudget((25)* player.getPurchasedRailRoadList().size());
				return " paid rent for "+ railRoadName;
			}
			else {
				return " goes bankrupt";
			}		
		}
		
		else if (railRoadList.contains(railRoadID)) {	// Purchase process for railroad.
			
			if (getBudget()>= Integer.parseInt(railRoadCost)) {
				
				owned.add(railRoadID);
				owned.add(railRoadName);
				owned.add(railRoadCost);
			
				ownedName.add(railRoadName);
				
				railRoadList.remove(railRoadID);					// These commands will remove the railroad that is sold.	
				railRoadList.remove(railRoadName);
				railRoadList.remove(railRoadCost);
				
				purchasedRailRoadList.add(railRoadID);					// This list will keep the number of the railroads purchased by player.
								
				setBudget(-Integer.parseInt(railRoadCost));
				
				banker.setBudgetForBanker(Integer.parseInt(railRoadCost));
				return " bought "+ railRoadName;
			}
			else {
				return " goes bankrupt";
			}
			
		}	
		else if (purchasedRailRoadList.contains(railRoadID)) {
			railRoadName = owned.get(owned.indexOf(railRoadID)+1);
			return " has "+railRoadName;
		}
		
		return "";
	}
	
	public String company(Banker banker, Player player, int move) {
		
		for (int i = 1; i <= 40; i++) {
		board[i-1] = String.valueOf(i);
	}
		
		String companyID =board[position-1]; 
		String companyName = company.get(company.indexOf(companyID)+1);
		String companyCost= company.get(company.indexOf(companyID)+2);
	
		if (player.owned.contains(companyID) && company.contains(companyID)) {  // Statement for rent.Checks for whether the other player purchased that place.
			if (getBudget()>= 4*move) {
				setBudget((-4)* move);
				player.setBudget((4)* move);
				return " paid rent for "+ companyName;
			}
			else {
				return " goes bankrupt";
			}
			
		}
		
		else if (companyList.contains(companyID)) {  				// Purchase process for company.
			if (getBudget()>= Integer.parseInt(companyCost)) {
				owned.add(companyID);
				owned.add(companyName);
				owned.add(companyCost);
			
				ownedName.add(companyName);
				
				companyList.remove(companyID);					// These commands will remove the company that is sold.
				companyList.remove(companyName);
				companyList.remove(companyCost);
			
				purchasedcompanyList.add(companyID);					// This list will keep the number of the companies purchased by player.
				
				setBudget(-Integer.parseInt(companyCost));
				banker.setBudgetForBanker(Integer.parseInt(companyCost));
				return " bought "+ companyName;
			}
			
			else {
				return " goes bankrupt";
			}
			
		}	
		else if (purchasedcompanyList.contains(companyID)) {
			companyName = owned.get(owned.indexOf(companyID)+1);
			return " has "+companyName;
		}
		
		return "";
	}

	public ArrayList<String> getPurchasedlandList() {
		return purchasedlandList;
	}

	public void setPurchasedlandList(ArrayList<String> purchasedlandList) {
		this.purchasedlandList = purchasedlandList;
	}

	public ArrayList<String> getPurchasedcompanyList() {
		return purchasedcompanyList;
	}

	public void setPurchasedcompanyList(ArrayList<String> purchasedcompanyList) {
		this.purchasedcompanyList = purchasedcompanyList;
	}

	public ArrayList<String> getPurchasedRailRoadList() {
		return purchasedRailRoadList;
	}

	public void setPurchasedRailRoadList(ArrayList<String> purchasedRailRoadList) {
		this.purchasedRailRoadList = purchasedRailRoadList;
	}
	
}
