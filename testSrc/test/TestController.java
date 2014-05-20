package test;

import static org.junit.Assert.*;

import org.junit.Test;

import yan.Cell;
import yan.Controller;

public class TestController {

	@Test
	public void testCreateGame() {
		Controller controller = new Controller();
		controller.newGame();
		
		
	}

}
