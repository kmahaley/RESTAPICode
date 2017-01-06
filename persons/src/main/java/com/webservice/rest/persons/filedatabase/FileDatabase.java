package com.webservice.rest.persons.filedatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.webservice.rest.persons.exception.DataNotFoundException;
import com.webservice.rest.persons.model.Person;


public class FileDatabase implements  PeopleProcessor{
	private static List<Person> list=new ArrayList<Person>();
	private static int count=0;
	static Logger log = Logger.getLogger(FileDatabase.class);
	public FileDatabase(){}
	
	@Override
	public List<Person> processFile(String fileName) {
		try {
			File f = new File(fileName);
			FileReader fr=new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				addDataToList(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			log.error("File not found = "+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public void addDataToList(String line) {
		String[] str = line.split(",");
		if (str.length > 1) {
			char code = str[0].charAt(0);

			switch (code) {
			case 'A':
				Person p = addPerson(list.size(), str[1], Integer.parseInt(str[2]));
				break;
			case 'U':
				updatePerson(str[1], str[2], str[3]);
				break;
			case 'D':
				deletePerson(str[1]);
				break;
			default:
				;
			}
		}
	}

	
	public Person addPerson(int size, String name, int age) {
		count++;
		Person personToAdd= new Person(size+count,name,age);
		list.add(personToAdd);
		return personToAdd;
	}

	public void updatePerson(String id, String name, String age) {
		try {
			int idvalue = Integer.parseInt(id);
			int agevalue = Integer.parseInt(age);
			Person p = null;
			for (Person person : list) {
				if (person.getId() == idvalue) {
					p = person;
				}
			}
			list.remove(p);
			p.setAge(agevalue);
			p.setName(name);
			list.add(p);
		} catch (IllegalStateException e) {
			log.error("IllegalStateException of the list =" + e.getMessage());
		}
	}
	
	public void deletePerson(String id) {
		int idvalue=Integer.parseInt(id);
		Person temp=null;
		for (Person person : list) {
			if(person.getId() == idvalue)
				temp=person;
		}
		if(temp==null)
			throw new DataNotFoundException("Person with id " + id + " for deletion");
		else
			list.remove(temp);
	}
	
	public Person getPerson(int id) {
		for (Person person : list) {
			if (person.getId() == id)
				return person;

		}
		return null;
	}
	
	/*
	 * Overloaded method
	 */
	public Person updatePerson(int personId, Person p) {
		Person temp = null;
		for (Person person : list) {
			if (person.getId() == personId) {
				person.setAge(p.getAge());
				person.setName(p.getName());
				temp = person;
			}
		}
		return temp;
	}
	
}
