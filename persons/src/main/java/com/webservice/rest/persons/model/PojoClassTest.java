package com.webservice.rest.persons.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PojoClassTest {
	Person person =new Person(1,"Mary",27);
	ErrorCode errorCode =new ErrorCode(100,"Test Message");
	
	@Test (timeout = 1000)
    public void testPersonIdAge() {
        assertTrue(person.getAge() == 27);
        assertEquals(1, person.getId());
        
        assertTrue(errorCode.getMessage().equals("Test Message"));
        assertFalse(errorCode.getErrorCode()==200);
    }
}
