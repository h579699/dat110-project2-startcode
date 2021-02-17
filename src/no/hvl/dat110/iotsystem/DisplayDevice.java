package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// TODO - START
				
		// create a client object and use it to
		Client broker = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		// - connect to the broker
		broker.connect();
		// - create the temperature topic on the broker
		broker.createTopic(Common.TEMPTOPIC);
		// - subscribe to the topic
		broker.subscribe(Common.TEMPTOPIC);
		
		// - receive messages on the topic
		for(int i = 0; i < COUNT; i++) {
			PublishMsg msg = (PublishMsg)broker.receive();
			System.out.println("DISPLAY: " + msg.getMessage());
		}
		
		// - unsubscribe from the topic
		broker.unsubscribe(Common.TEMPTOPIC);
		// - disconnect from the broker
		broker.disconnect();
		
		// TODO - END
		
		System.out.println("Display stopping ... ");
		
	}
}
