package default_1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJUnit {
	String message = "Hello World";	
	   MessageUtil messageUtil = new MessageUtil(message);

	   @Test
	   public void testPrintMessage() {	  
	      assertEquals(message,messageUtil.printMessage());
	   }
}
