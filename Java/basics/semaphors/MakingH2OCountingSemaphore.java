package basics.semaphors;


import java.util.concurrent.Semaphore;

public class MakingH2OCountingSemaphore {
	private final Semaphore hydrogenSemaphore;
	private final Semaphore oxygenSemaphore;

	public MakingH2OCountingSemaphore() {
		hydrogenSemaphore = new Semaphore(2); // Allows 2 hydrogen atoms
		oxygenSemaphore = new Semaphore(1);   // Allows 1 oxygen atom
	}

	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		hydrogenSemaphore.acquire(); // Wait for permission to create hydrogen
		releaseHydrogen.run();       // Release hydrogen atom
		if (hydrogenSemaphore.availablePermits() == 0) {
			oxygenSemaphore.release(); // If both hydrogen atoms are available, release oxygen
		}
	}

	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		oxygenSemaphore.acquire();   // Wait for permission to create oxygen
		releaseOxygen.run();         // Release oxygen atom
		hydrogenSemaphore.release(2); // Release 2 permits for hydrogen (used by 2 hydrogen atoms)
	}
}