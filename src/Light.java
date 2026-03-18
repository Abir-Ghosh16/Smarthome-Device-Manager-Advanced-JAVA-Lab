@Manufacturer(name = "HomeEase")
class Light extends Device {
    public Light(String deviceId) {
        super(deviceId, DeviceType.LIGHT);
    }

    @Override
    public void start() {
        System.out.println("Light " + id + " is now ON.");
    }
}