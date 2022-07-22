import java.util.concurrent.Semaphore;

public class CarThread extends Thread {
	Semaphore sem;
	String threadName;
	public Intersection intersection;

	// Generator
	public CarThread(Semaphore sem, String threadName, Intersection intersection) {
		super(threadName);
		this.sem = sem;
		this.threadName = threadName;
		this.intersection = intersection;
	}

	@Override
	public void run() {
		// run by thread NS
		if(this.getName().equals("NS")) {
			try {
				// First, get a permit.
				// acquiring the lock
				sem.acquire();
				intersection.printMap();
				Thread.sleep(1000);
				// Now, accessing the shared resource.
				// The cars start to move in the South direction until the road has no car left
				while(intersection.isCarNorthSouth()) {
					for (int i = 8; i >= 0; i--) {
						if(intersection.intersectionMap.get(i).get(4) instanceof Car) {
							// Unless is not on the last cell, the car moves one cell forward and leaves behind
							// a Lane Cell
							if(i != 8) {
								intersection.intersectionMap.get(i).set(4, new Lane(i,4));
								intersection.intersectionMap.get(i + 1).set(4, new Car(i + 1,4));
							} else {
								intersection.intersectionMap.get(8).set(4, new Lane(8, 4));
							}
						}
					}
					intersection.printMap();
					Thread.sleep(1000);
				}
			} catch (InterruptedException exc) {
				System.out.println(exc);
			}
		}

		// run by thread WE
		else {
			try {
				// First, get a permit.
				// acquiring the lock
				sem.acquire();
				Thread.sleep(1000);
				// Now, accessing the shared resource.
				// The cars start to move in the East direction until the road has no car left
				while(intersection.isCarWestEast()) {
					for (int i = 8; i >= 0; i--) {
						if(intersection.intersectionMap.get(4).get(i) instanceof Car) {
							// Unless is not on the last cell, the car moves one cell forward and leaves behind
							// a Lane Cell
							if(i != 8) {
								intersection.intersectionMap.get(4).set(i, new Lane(4,i));
								intersection.intersectionMap.get(4).set(i + 1, new Car(4,i + 1));
							} else {
								intersection.intersectionMap.get(4).set(8, new Lane(4, 8));
							}
						}
					}
					intersection.printMap();
					Thread.sleep(1000);
				}
			} catch (InterruptedException exc) {
				System.out.println(exc);
			}
		}
		sem.release();
	}
}
