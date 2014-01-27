package test;
import org.junit.Assert.*;
import org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.*;


public class ExceptionsTest {
	//Let's import Mockito statically so that the code looks clearer
	private UserInputException uie;
	private InvalidInputException iie;
	
	@Before
	public void setUp(){
		uie = new UserInputException();
		iie = new InvalidInputException();
	}
	
	@Test
	public void TestGetMessageIIE() {
		this.iie.setMessage("asd");
		assertEquals(iie,this.iie.getMessage());
	}
	@Test
	public void TestGetMessageUIE() {
		this.uie.setMessage("asd");
		assertEquals(uie,this.uie.getMessage());
	}
}
