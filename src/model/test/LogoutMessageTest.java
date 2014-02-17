package model.test;

import static org.junit.Assert.*;
import model.LogoutMessage;

import org.junit.Before;
import org.junit.Test;

public class LogoutMessageTest {
	private LogoutMessage loum, loum1;

	@Before
	public void setUp() throws Exception {
		loum = new LogoutMessage();
		loum1 = new LogoutMessage(null);
	}

}
