import com.cluedo.game.CluedoMap;
import com.cluedo.game.Notebook;
import com.cluedo.game.Player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class TestNotebook {

    Player testplayer = new Player(0,0, new CluedoMap());
    Notebook notebook;

    @Test
    public void testValueRoomCard() {
        notebook = Notebook.getInstance(testplayer);
        notebook.yourRoomCards();

        testplayer.setMyRoomCard();
        Assert.assertEquals(testplayer.getMyRoomCard().getValue(), notebook.valueRoomCard);
    }
}
