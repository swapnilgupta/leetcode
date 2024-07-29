package basics;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {

	private final BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				String message = "Message " + i;
				queue.put(message);
				System.out.println("Produced: " + message);
				Thread.sleep(1000); // Simulating some work
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

class Consumer implements Runnable {

	private final BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = queue.take();
				System.out.println("Consumed: " + message);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>();

		// Creating producer and consumer threads
		Thread producerThread = new Thread(new Producer(queue));
		Thread consumerThread = new Thread(new Consumer(queue));

		// Starting the threads
		producerThread.start();
		consumerThread.start();
	}
}
