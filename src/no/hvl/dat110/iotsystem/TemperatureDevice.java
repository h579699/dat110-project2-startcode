package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start

		// create a client object and use it to
		Client broker = new Client("temperatursensor", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker
		broker.connect();
		
		String temp = "";
		// - publish the temperature(s)
		for(int i = 0; i < COUNT; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp = "" + sn.read();
			System.out.println("READING: " + temp);
			broker.publish(Common.TEMPTOPIC, temp);;
		}
		
		// - disconnect from the broker
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		broker.disconnect();

		// TODO - end

		System.out.println("Temperature device stopping ... ");

	}
}
