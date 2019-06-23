import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static Locations locations = new Locations();
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("This is a small simulation of the classic game Colossal Cave Adventure");
		System.out.println("You can use phrases like \"Go North\" \"Move Up\" \"Move in the northeast direction\" etc");
		
		Scanner scanner = new Scanner(System.in);
		
		Map<String, String > vocabulary = new HashMap<>();
		vocabulary.put("QUIT", "Q");
		vocabulary.put("NORTH", "N");
		vocabulary.put("SOUTH", "S");
		vocabulary.put("WEST", "W");
		vocabulary.put("EAST", "E");
		vocabulary.put("UP", "U");
		vocabulary.put("DOWN", "D");
		vocabulary.put("NORTHEAST", "NE");
		vocabulary.put("NORTHWEST", "NW");
		vocabulary.put("SOUTHEAST", "SE");
		vocabulary.put("SOUTHWEST", "SW");
		vocabulary.put("NORTH EAST", "NE");
		vocabulary.put("NORTH WEST", "NW");
		vocabulary.put("SOUTH EAST", "SE");
		vocabulary.put("SOUTH WEST", "SW");
		vocabulary.put("NORTH-EAST", "NE");
		vocabulary.put("NORTH-WEST", "NW");
		vocabulary.put("SOUTH-EAST", "SE");
		vocabulary.put("SOUTH-WEST", "SW");
		
		
		Location currentLocation = locations.getLocation(1);
		while(true) {
			System.out.println(currentLocation.getDescription());
			if(currentLocation.getLocationID() == 0) {
				break;
			}
			
			Map<String,Integer> exits = currentLocation.getExits();
			
			System.out.print("Available exits are: ");
			for(String exit: exits.keySet()) {
				System.out.print(exit + ", ");
			}
			System.out.println();
			
			String direction = scanner.nextLine().toUpperCase();
			if(direction.length() > 1) {
				String[] words = direction.split(" ");
				for(String word: words) {
					if(vocabulary.containsKey(word)) {
						direction = vocabulary.get(word);
						break;
					}
				}
			}
			
			if(exits.containsKey(direction)) {
				currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
			} else {
				System.out.println("You cannot go in that direction");
			}
		}
		locations.close();
	}
}
