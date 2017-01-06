package com.webservice.rest.persons.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.webservice.rest.persons.exception.DataNotFoundException;
import com.webservice.rest.persons.filedatabase.FileDatabase;
import com.webservice.rest.persons.model.Person;


public class PersonService {
	static Logger log = Logger.getLogger(PersonService.class);
	private static List<Person> servicelist=new ArrayList<Person>();
	private FileDatabase filedb=new FileDatabase();
	
	public PersonService(){}
	
	public PersonService(String fileName){
		servicelist=filedb.processFile(fileName);
	}
	
	public List<Person> getAllPersons() {
		List<Person> list=servicelist;
		return list;
	}
	
	public Person getPerson(int id) {
		Person person = filedb.getPerson(id);
		 if(person== null){
			 log.error("Person with id "+id+" not found");
			 throw new DataNotFoundException("Person with id "+id+" not found");
		 }
		 return person;
	}
	
	public Person addPerson(Person p) {
		Person newPerson=filedb.addPerson(p.getId(),p.getName(),p.getAge());
		return newPerson;
	}
	
	public Person updatePerson(int personId,Person p) {
		Person temp = filedb.updatePerson(personId,p);
		if(temp==null){
			log.error("Update, Person with id " + p.getId() + " not found for update");
			throw new DataNotFoundException("Update, Person with id " + p.getId() + " not found for update");
		}		
		return temp;
	}
	
	public void removePerson(int personId) {
		try {
			Person person = filedb.getPerson(personId);
			if(person== null){
				 log.error("Delete, Person with id "+personId+" not found");
				 throw new DataNotFoundException("Delete, Person with id "+personId+" not found");
			 }else
				 filedb.deletePerson(String.valueOf(personId));
		} catch (IllegalStateException e) {
			log.error("IllegalStateException of the list ="+e.getMessage());
		} catch(NullPointerException e){
			log.error("Null Pointer exception in the list ="+e.getMessage());
		}
	}
}
