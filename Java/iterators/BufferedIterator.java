package iterators;


import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Designing a buffered iterator which will use another iterator with next() and hasNext() interface
 * and will asynchronously fetch data from this iterator. You need to design this buffered iterator
 * with next() and hasNext() interface. hasNext() of BufferedIterator will be blocked if
 * inputIterator doesn't have data
 */
public class BufferedIterator<T> implements Iterator<T> {

    private final Iterator<T> inputIterator;
    private final BlockingQueue<T> buffer;

    public BufferedIterator(Iterator<T> inputIterator) {
        this.inputIterator = inputIterator;
        this.buffer = new LinkedBlockingQueue<>();
    }

    @Override
    public boolean hasNext() {
        if (!buffer.isEmpty()) {
            return true; // Buffer has data, so hasNext is true
        }

        // Asynchronously fetch data from the input iterator and fill the buffer
        new Thread(() -> {
            while (inputIterator.hasNext()) {
                try {
                    buffer.put(inputIterator.next());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Error while buffering data", e);
                }
            }
        }).start();

        // Block until the buffer has data
        while (buffer.isEmpty()) {
            try {
                Thread.sleep(100); // Sleep for a short interval to avoid busy-waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Error while waiting for data", e);
            }
        }

        return true;
    }

    @Override
    public T next() {
        return buffer.poll(); // Retrieve and remove the head of the buffer
    }
}

