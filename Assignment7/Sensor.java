/**
 * There are also two sensors, one for heat and one for pressure, which can be
 * used to monitor the device.
 */
class Sensor extends Thread {
	private final Device device;
	private double value;

	public Sensor(Device device) {
		this.device = device;
	}

	public double getValue() {
		return value;
	}

	public void updateValue() {
		this.value += 0.001; // you check with other values here and see how it works
	}

	public void run() {
		while (true) {
			synchronized (device) {
				double oldValue = value;
				updateValue();
				if (value != oldValue) {
					device.notify();  //signals the controller if value has changed
				}
			}
		}
	}
}