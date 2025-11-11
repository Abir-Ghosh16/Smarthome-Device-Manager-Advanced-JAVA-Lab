
abstract class Device {
    protected String id;
    protected DeviceType type;

    public Device(String id, DeviceType type) {
        this.id = id;
        this.type = type;
    }

    public abstract void start();

    public void showInfo() {
        System.out.println("Device ID: " + id + ", Type: " + type);
    }

    public DeviceType getType()
    {
        return this.type;
    }

    public String getId()
    {
        return this.id;
    }
}