package basics;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {

	private int data = 0;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();

	public int readData() {
		lock.readLock().lock();
		try {
			// Reading operation
			return data;
		} finally {
			lock.readLock().unlock();
		}
	}

	public void writeData(int newValue) {
		lock.writeLock().lock();
		try {
			// Writing operation
			data = newValue;
		} finally {
			lock.writeLock().unlock();
		}
	}
}

public class ReaderWriter {

	public static void main(String[] args) {
		SharedResource sharedResource = new SharedResource();

		// Create reader threads
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				int value = sharedResource.readData();
				System.out.println(
					"Reader Thread " + Thread.currentThread().getId() + " read: " + value);
			}).start();
		}

		// Create writer threads
		for (int i = 0; i < 2; i++) {
			final int newValue = i + 1;
			new Thread(() -> {
				sharedResource.writeData(newValue);
				System.out.println(
					"Writer Thread " + Thread.currentThread().getId() + " wrote: " + newValue);
			}).start();
		}
	}
}

