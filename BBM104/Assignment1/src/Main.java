
public class Main {

	public static void main(String[] args) {
		
		People personList = new People();
		personList.listAllPerson();
		
		Food foodList = new Food();
		foodList.listAllFoods();
		
		Sport sportList = new Sport();
		sportList.listAllSports();
		
		if (args[0].equals("command.txt")) {
			Commands command = new Commands();
			command.getCommand();

		}
	}

}
