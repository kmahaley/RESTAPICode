package com.webservice.rest.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.webservice.rest.messenger.model.Message;
import com.webservice.rest.messenger.model.Profile;

public class DatabaseClass {
	private static Map<Integer, Message> messages=new HashMap<Integer, Message>();
	private static Map<Integer, Profile> profiles=new HashMap<Integer, Profile>();
	
	
	public static Map<Integer, Message> getMessages() {
		return messages;
	}
	public static Map<Integer, Profile> getProfiles() {
		return profiles;
	}
	
	
}
