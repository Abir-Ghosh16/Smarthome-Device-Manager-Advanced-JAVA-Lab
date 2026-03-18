public static void main(String[] args) {
   DeviceManager<Device> manager = new DeviceManager<>();

    manager.addDevice(new Light("L1"));
    manager.addDevice(new Thermostat("T1"));
    manager.addDevice(new Camera("C1"));

    System.out.println("Devices Starting");
    manager.startAllDevices();


}


