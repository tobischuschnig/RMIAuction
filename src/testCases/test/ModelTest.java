package test;

import org.junit.Assert.*;
import org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.*;


public class ModelTest {
	private SuccessMessage sm;
	private ErrorMessage em;
	private BidMessage bm;
	private CreateMessage cm;
	private LoginMessage linm;
	private LogoutMessage loum;
	
	@Before
	public void setUp(){
		sm = new SuccessMessage("Sucess");
		em = new ErrorMessage("Error");
		bm = new BidMessage("Username", 100, 100.50);
		cm = new CreateMessage("Username", "Was zum Verkaufen", 100);
		linm = new LoginMessage("Username");
		loum = new LogoutMessage("Username");
	}
	
	@Test
	public void TestGetNameSM() {
		this.sm.getName();
	}
	
	@Test
	public void TestGetNameEM() {
		this.em.getName();
	}
	
	@Test
	public void TestGetNameBM() {
		this.bm.getName();
	}
	
	@Test
	public void TestGetNameCM() {
		this.cm.getName();
	}
	
	@Test
	public void TestGetNameLINM() {
		this.linm.getName();
	}
	
	@Test
	public void TestGetNameLOUM() {
		this.loum.getName();
	}
	
	@Test
	public void TestGetContentSM() {
		this.sm.getContent();
	}
	
	@Test
	public void TestGetContentEM() {
		this.em.getContent();
	}
