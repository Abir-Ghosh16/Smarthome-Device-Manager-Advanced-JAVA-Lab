@Manufacturer(name = "HomeEase")
class Thermostat extends Device {
    public Thermostat(String deviceId) {
        super(deviceId, DeviceType.THERMOSTAT);
    }

    @Override
    public void start() {
        System.out.println("Thermostat " + id + " is On.");
    }
}