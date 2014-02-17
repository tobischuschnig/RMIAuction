package model.test;

import static org.junit.Assert.*;
import model.LoginMessage;

import org.junit.Before;
import org.junit.Test;

public class LoginMessageTest {
	private LoginMessage linm, linm1;

	@Before
	public void setUp() throws Exception {
		linm = new LoginMessage();
		linm1 = new LoginMessage(null, null, 0, 0);
	}

}
