package model.test;

import static org.junit.Assert.*;
import model.Event;

import org.junit.Before;
import org.junit.Test;

public class EventTest {
	private Event e;

	@Before
	public void setUp() throws Exception {
		e = new Event(null, null, 0);
	}

}
