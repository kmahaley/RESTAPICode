package com.webservice.rest.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.webservice.rest.messenger.database.DatabaseClass;
import com.webservice.rest.messenger.exception.DataNotFoundException;
import com.webservice.rest.messenger.model.Message;

public class MessageService {

	private Map<Integer, Message> messages = DatabaseClass.getMessages();

	public MessageService(){
		messages.put(1, new Message(1, "Message 1", "john"));
		messages.put(2, new Message(2, "Message 2", "Pineapple"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	/*
	 * For specific parameter like year
	 */
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message m : messages.values()) {
			cal.setTime(m.getCreated());
			if (cal.get(Calendar.YEAR) == year)
				messageForYear.add(m);
		}
		return messageForYear;
	}

	/*
	 * For pagination
	 */
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	public Message getMessage(int id) {
		 Message message = messages.get(id);
		 if(message== null)
			 throw new DataNotFoundException("Message with id "+id+" not found");
		 
		 return message;
	}

	public Message addMessage(Message m) {
		m.setId(messages.size() + 1);
		messages.put(m.getId(), m);
		return m;
	}

	public Message updateMessage(Message m) {
		if (m.getId() <= 0)
			return null;
		messages.put(m.getId(), m);
		return m;
	}

	public Message removeMessage(int id) {
		return messages.remove(id);

	}
}
