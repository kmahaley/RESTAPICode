package com.webservice.rest.persons.filedatabase;

import java.util.List;

import com.webservice.rest.persons.model.Person;

public interface PeopleProcessor {
	/**
	This method takes in a filename, opens the CSV file specified, processes
	the contents, and returns a list of the Person objects currently in the
	system.
	*/
	List<Person> processFile(String fileName);
}
