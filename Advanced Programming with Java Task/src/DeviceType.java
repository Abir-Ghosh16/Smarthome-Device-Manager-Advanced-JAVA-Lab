public enum DeviceType {
    LIGHT("Smart Light", 50),
    THERMOSTAT("Smart Thermostat", 70),
    CAMERA("Smart Camera", 90);

    private final String label;
    private final int powerRating;

    DeviceType(String label, int powerRating) {
        this.label = label;
        this.powerRating = powerRating;
    }

    public int getPowerRating() {
        return powerRating;
    }

    @Override
    public String toString() {
        return label + " Power: " + powerRating + "W |";
    }
}
