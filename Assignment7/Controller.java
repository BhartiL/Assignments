/**
 * 
 * Write a class Controller (extends Thread) that can poll the sensors
 * concurrently to running the device. You should implement its run() method
 * such that it starts the device and then monitors it by waiting for and
 * examining any new sensor values. The controller shuts down the device if the
 * heat sensor exceeds the value 70 or the pressure sensor the value 100. Also
 * complete the run() method in the class Sensor which calls updateValue()
 * continuously and signals the controller if its value has changed. You can
 * print the heat and pressure value to console in the run() method of
 * Controller to check.
 *
 */
public class Controller extends Thread {
	private Sensor heat;
	private Sensor pressure;
	private Device device;

	public Controller(Device device, Sensor heat, Sensor pressure) { // Controller constructor
		this.device = device;
		this.heat = heat;
		this.pressure = pressure;
	}

	public void run() {
		device.startup(); // device starts up
		synchronized (device) {
			while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
				try {
					device.wait();
					System.out.println("heat-> " + heat.getValue() + ", pressure-> " + pressure.getValue());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		device.shutdown(); // device gets shutdown
	}

}
