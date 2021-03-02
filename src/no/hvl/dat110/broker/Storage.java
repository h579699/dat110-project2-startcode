package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;
	
	// buffer for meldinger til subscribers som er disconnected
	// mapper bruker til buffrede meldinger
	
	protected ConcurrentHashMap<String, Set<Message>> buffer;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		buffer = new ConcurrentHashMap<String, Set<Message>>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		ClientSession session = new ClientSession(user, connection);
		clients.put(user, session);
		
	}

	public void removeClientSession(String user) {

		// TODO: remove client session for user from the storage
		ClientSession session = getSession(user);
		clients.remove(user, session);
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage
		subscriptions.put(topic, new HashSet<String> ());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage
		subscriptions.remove(topic);
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		subscriptions.get(topic).add(user);
		
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic
		subscriptions.get(topic).remove(user);
	}
	
	public void createBuffer(String user) {
		buffer.put(user, new HashSet<Message> ());
	
	}
	
	public void addBufferMessage(String user, Message msg) {
		buffer.get(user).add(msg);
		
	}

	public Set<Message> getBufferedMessages(String user) {
		return buffer.get(user);
	}
}
