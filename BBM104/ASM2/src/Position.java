public class Position extends Money{
	
	private String jailString;
	public int position =1;
	public int count =1;
	private int oldPosition;
	private int countFreePark =1;
	private int countFreePark2 =1;
	
	public int getPosition(int move) {
		oldPosition = position;	
		String jailString = jail(move);
		String parkString = freeParking(move);
		if (position >40) {
			position = move -(40-oldPosition);
		}
		
		return position;
	}
		
	public String jail(int move) {
		
		if (position == 11) {
			count+=1;
			position = position;		
			if (count ==5) {
				position= position+move;
				count =0;
			}
			return "went to jail";
			
		}
		else {
			if (position == 21 && countFreePark2< 2 ) {
				countFreePark2+=1;
				position = position;
			}
			else {
				position= position+move;
			}
			
		}
		return "";
	}
	
	public String goToJail() {
		if(position == 31) {
			position = 11;
			return " went to jail";
		}
		return "";
	}
	
	public String freeParking(int move) {		
		if (position == 21) {
			countFreePark+=1;
			position = position;		
			return " is in Free Parking.";
		}
		return "";
	}
	
	public int getOldPosition() {
		return oldPosition;
	}

	public String getJailString() {
		return jailString;
	}
}
