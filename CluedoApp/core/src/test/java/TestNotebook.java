import com.cluedo.game.Cluedo;
import com.cluedo.game.CluedoMap;
import com.cluedo.game.Notebook;
import com.cluedo.game.Player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public class TestNotebook {

    Player testplayer = new Player(0,0, new CluedoMap());
    Notebook notebook;

    @Test
    public void testValueRoomCard() {
        notebook.getInstance(testplayer);
        notebook.yourRoomCards();

        testplayer.setMyRoomCard();
        Assert.assertEquals(testplayer.getMyRoomCard().getValue(), notebook.valueRoomCard);
    }
}
