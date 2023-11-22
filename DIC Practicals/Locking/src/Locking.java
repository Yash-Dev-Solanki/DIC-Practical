import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Database {
    private Map<String, String> data = new HashMap<>();
    private Map<String, Lock> locks = new HashMap<>();

    public void write(String key, String value) {
        Lock lock = getLock(key);
        lock.lock();
        try {
            data.put(key, value);
            System.out.println("Write - Key: " + key + ", Value: " + value);
        } finally {
            lock.unlock();
        }
    }

    public String read(String key) {
        Lock lock = getLock(key);
        lock.lock();
        try {
            String value = data.get(key);
            System.out.println("Read - Key: " + key + ", Value: " + value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    private Lock getLock(String key) {
        locks.putIfAbsent(key, new ReentrantLock());
        return locks.get(key);
    }
}

public class Locking {
    public static void main(String[] args) {
        Database database = new Database();

        // Example of transactions using the database
        Runnable transaction1 = () -> {
            database.write("key1", "value1");
            database.read("key1");
        };

        Runnable transaction2 = () -> {
            database.read("key1");
            database.write("key1", "value2");
        };

        // Run transactions in separate threads
        Thread t1 = new Thread(transaction1);
        Thread t2 = new Thread(transaction2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
