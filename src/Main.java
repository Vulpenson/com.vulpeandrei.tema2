import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Intersection intersection = Intersection.getIntersection();
		Semaphore sem = new Semaphore(1);

		// Creating two threads for the North-South road and West-East road
		CarThread mt1 = new CarThread(sem, "NS", intersection);
		CarThread mt2 = new CarThread(sem, "WE", intersection);

		// stating threads NS and WE
		mt1.start();
		mt2.start();
		// waiting for threads NS and WE
		mt1.join();
		mt2.join();
	}
}
