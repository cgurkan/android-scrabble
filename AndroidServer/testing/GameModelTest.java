import static org.junit.Assert.*;
import model.GameModel;

import org.junit.Before;
import org.junit.Test;


public class GameModelTest {
	
	GameModel gm = null;
	
	@Before
	public void setUp(){
		GameModel gm = new GameModel("Lisa", "Mike");
	}
	
	@Test
	public void testChangeTurn() {
		boolean changedTurn = false;
		
		gm.getPlayer1().setTurn(false);
		gm.changeTurn();
		changedTurn = true;
		assertTrue(changedTurn == gm.getPlayer1().isTurn());
	}

}
