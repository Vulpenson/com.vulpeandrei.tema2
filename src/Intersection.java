import java.util.ArrayList;

public class Intersection {
	public ArrayList<ArrayList<Cell>> intersectionMap = new ArrayList<ArrayList<Cell>>();
	private static Intersection intersection = null;

	// Generator prepares the intersectionMap
	// Legend of the map:
	// * -> Sidewalk (those cells are not accessible by car)
	// - or | -> Lane (Cells accessible by car)
	// c -> Car
	private Intersection() {
		for (int i = 0; i < 9; i++) {
			intersectionMap.add(new ArrayList<Cell>());
			for (int j = 0; j < 9; j++) {
				intersectionMap.get(i).add(new Sidewalk(i,j));
			}
		}
		for (int i = 0; i < 9; i++) {
			intersectionMap.get(4).set(i, new Lane(4,i));
		}
		for (int i = 0; i < 9; i++) {
			intersectionMap.get(i).set(4, new Lane(i, 4));
		}
		for (int i = 0; i < 4; i++) {
			intersectionMap.get(4).set(i, new Car(4,i));
		}
		for (int i = 0; i < 4; i++) {
			intersectionMap.get(i).set(4, new Car(i,4));
		}
	}

	// Singleton method to get an instance of the intersection
	public static Intersection getIntersection() {
		if (intersection == null) {
			intersection = new Intersection();
		}
		return intersection;
	}

	// Method used to print the intersection map
	public void printMap() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(intersectionMap.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	// Method used to verify if there are any cars left on the North-South route
	public boolean isCarNorthSouth() {
		for (int i = 0; i < 9; i++) {
			if (intersectionMap.get(i).get(4) instanceof Car) {
				return true;
			}
		}
		return false;
	}

	// Method used to verify if there are any cars left on the West-East route
	public boolean isCarWestEast() {
		for (int i = 0; i < 9; i++) {
			if (intersectionMap.get(4).get(i) instanceof Car) {
				return true;
			}
		}
		return false;
	}
}
