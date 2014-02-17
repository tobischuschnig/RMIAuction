package model.test;

import static org.junit.Assert.*;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

public class UserEventTest {
	private UserEvent ue, ue1;

	@Before
	public void setUp() throws Exception {
		ue = new UserEvent(null, null, 0);
		ue1 = new UserEvent(null, null, 0, null);
	}
}
