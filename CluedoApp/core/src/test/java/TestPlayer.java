import com.cluedo.game.CluedoMap;
import com.cluedo.game.Player;

import org.junit.Assert;
import org.junit.Test;

public class TestPlayer {

    Player testplayer = new Player(0,0, new CluedoMap());

    @Test
    public void testSetX() {
        testplayer.setX(32);
        Assert.assertEquals(32, testplayer.getX());
    }

    @Test
    public void testSetY() {
        testplayer.setY(32);
        Assert.assertEquals(32, testplayer.getY());
    }

    @Test
    public void testNotValid() {
        Assert.assertFalse(testplayer.valid(-1,33*40));
    }

    @Test
    public void testValid() {
        Assert.assertTrue(testplayer.valid(2*32,4*32));
    }

    @Test
    public void testCheckIfPlayerIsInRoom() {
        Assert.assertTrue(testplayer.checkIfPlayerIsInRoom(7*32,6*32));
    }

    @Test
    public void testCheckIfPlayerIsNotInRoom() {
        Assert.assertFalse(testplayer.checkIfPlayerIsInRoom(21*32,0));
    }



}
