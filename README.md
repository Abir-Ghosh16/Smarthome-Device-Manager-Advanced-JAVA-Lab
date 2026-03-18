# 🏠 SmartHome Device Manager

A Java-based **Smart Home IoT simulation** that demonstrates core Advanced Java Programming concepts — including abstract classes, generics, enums, custom annotations, multithreading, and inner classes — through a realistic smart device management system.

---

## 📁 Project Structure

```
SmartHomeDeviceManager/
├── src/
│   ├── Main.java              # Entry point
│   ├── Device.java            # Abstract base class for all devices
│   ├── DeviceType.java        # Enum — device types with power ratings
│   ├── DeviceManager.java     # Generic manager with threading & nested classes
│   ├── Manufacturer.java      # Custom runtime annotation
│   ├── Light.java             # Concrete device — Light
│   ├── Thermostat.java        # Concrete device — Thermostat
│   └── Camera.java            # Concrete device — Camera
└── README.md
```

---

## 🧠 Concepts Demonstrated

| Concept | Where Used |
|---|---|
| Abstract Class & Inheritance | `Device.java` → `Light`, `Thermostat`, `Camera` |
| Generics (`<T extends Device>`) | `DeviceManager<T>` |
| Enum with fields & methods | `DeviceType` (label, power rating) |
| Custom Annotation | `@Manufacturer` on device classes |
| Multithreading (`Thread`, `Runnable`) | `DeviceRunner` inner class in `DeviceManager` |
| Static Nested Class | `DeviceStats` inside `DeviceManager` |
| Non-Static Inner Class | `DeviceRunner` inside `DeviceManager` |
| `AtomicInteger` (thread-safe counter) | `DeviceStats.totalDevices`, `activeDevices` |

---

## 🔍 Class Breakdown

### `Device` (Abstract Class)
The base class for all smart devices. Defines the common contract — every device has an `id` and a `DeviceType`, and must implement `start()`.

```java
abstract class Device {
    protected String id;
    protected DeviceType type;
    public abstract void start();
}
```

---

### `DeviceType` (Enum)
Enumerates the supported device categories. Each type carries a human-readable label and a power consumption rating in watts.

| Enum Value | Label | Power Rating |
|---|---|---|
| `LIGHT` | Smart Light | 50W |
| `THERMOSTAT` | Smart Thermostat | 70W |
| `CAMERA` | Smart Camera | 90W |

---

### `DeviceManager<T>` (Generic Class)
The core manager that accepts any device subtype. Internally uses two nested classes:

- **`DeviceStats`** — static nested class using `AtomicInteger` for thread-safe counting of total and active devices.
- **`DeviceRunner`** — non-static inner class implementing `Runnable`, responsible for starting each device on its own thread and updating stats.

All devices are started **concurrently** using `Thread` + `join()` for synchronized completion.

---

### `@Manufacturer` (Custom Annotation)
A runtime-retained, class-level annotation that tags device classes with their manufacturer name and year.

```java
@Manufacturer(name = "HomeEase")
class Light extends Device { ... }
```

---

### Concrete Devices
`Light`, `Thermostat`, and `Camera` each extend `Device`, pass their type to the superclass constructor, and implement `start()` with a status message. All three are tagged with `@Manufacturer(name = "HomeEase")`.

---

## ▶️ How to Run

### Prerequisites
- Java 8 or higher
- Any IDE (IntelliJ IDEA recommended) or terminal with `javac`

### Compile & Run (Terminal)

```bash
# Navigate to the src directory
cd src

# Compile all files
javac *.java

# Run the program
java Main
```

### Expected Output

```
Device added: Smart Light 50W | ID: L1
Device added: Smart Thermostat 70W | ID: T1
Device added: Smart Camera 90W | ID: C1

Devices Starting

Starting all devices...
Light On in thread: Thread-0 starting
Light L1 is now ON.
Thermostat On in thread: Thread-1 starting
Thermostat T1 is On.
Camera On in thread: Thread-2 starting
Camera C1 is ON.

Total Devices: 3, Active Devices: 3
...
Completed successfully!
Total Devices: 3, Active Devices: 0
```

> **Note:** Thread execution order may vary between runs — this is normal and expected multithreaded behavior.

---

## 🧵 Threading Flow

```
main thread
    └── DeviceManager.startAllDevices()
            ├── Thread-0 → DeviceRunner(Light L1)      → start() → sleep(20s) → stats update
            ├── Thread-1 → DeviceRunner(Thermostat T1) → start() → sleep(20s) → stats update
            └── Thread-2 → DeviceRunner(Camera C1)     → start() → sleep(20s) → stats update
                    ↓ (all threads joined before proceeding)
            "Completed successfully!" + final stats display
```

---

## 🏫 Course Info

**Course:** Advanced Programming with Java
**Topic:** OOP, Generics, Annotations, Multithreading, Nested Classes
**IDE:** IntelliJ IDEA

---
