
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceManager <T extends Device>
{
    private final List<T> devices;
    private final DeviceStats stats;

    public DeviceManager() {
        this.devices = new ArrayList<>();
        this.stats = new DeviceStats();
    }

    public void addDevice(T device) {
        devices.add(device);
        stats.incrementTotalDevices();
        System.out.println("Device added: " + device.getType() + " ID: " + device.getId() + " ");
    }

    public void startAllDevices() {
        System.out.println("\nStarting all devices...");

        List<Thread> threads = new ArrayList<>();

        for (T device : devices) {
            DeviceRunner runner = new DeviceRunner(device);
            Thread thread = new Thread(runner);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("\nCompleted successfully!");
        stats.displayStats();
    }

    // Static nested class for device statistics
    static class DeviceStats {
        private final AtomicInteger totalDevices;
        private final AtomicInteger activeDevices;

        public DeviceStats() {
            this.totalDevices = new AtomicInteger(0);
            this.activeDevices = new AtomicInteger(0);
        }

        public void incrementTotalDevices() {
            totalDevices.incrementAndGet();
        }

        public void incrementActiveDevices() {
            activeDevices.incrementAndGet();
        }

        public void decrementActiveDevices(){activeDevices.decrementAndGet();}

        public void displayStats() {
            System.out.println("Total Devices: " + totalDevices.get() +
                    ", Active Devices: " + activeDevices.get());
        }
    }

    // NonStatic Inner class
    class DeviceRunner implements Runnable {
        private final T device;

        public DeviceRunner(T device) {
            this.device = device;
        }

        @Override
        public void run() {
            if(device.getId().equals("L1"))
                System.out.println("Light On in thread: " + Thread.currentThread().getName() + " starting");
            else if(device.getId().equals("T1"))
                System.out.println("Thermostat On in thread: " + Thread.currentThread().getName() + " starting");
            else
                System.out.println("Camera On in thread: " + Thread.currentThread().getName() + " starting");
            device.start();
            stats.incrementActiveDevices();

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }
            stats.decrementActiveDevices();
            stats.displayStats();
            System.out.println(device.getType() + " completed in thread: " +
                    Thread.currentThread().getName());
        }
    }
}
