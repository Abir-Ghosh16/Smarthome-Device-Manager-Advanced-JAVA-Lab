@Manufacturer(name = "HomeEase")
class Camera extends Device {
    public Camera(String deviceId) {
        super(deviceId, DeviceType.CAMERA);
    }

    @Override
    public void start() {
        System.out.println("Camera " + id + " is ON.");
    }
}