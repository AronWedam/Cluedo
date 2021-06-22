import com.cluedo.game.Card;
import com.cluedo.game.CardHandOut;

import org.junit.Assert;
import org.junit.Test;

public class TestCardHandOut {

    private static CardHandOut cardHandOut = new CardHandOut();


    @Test
    public void testGetInstance(){
        Assert.assertTrue(true);
    }

    @Test
    public void testArrayListSuspectTrue(){
        cardHandOut.randomlyPickCardOfType(0);
        Assert.assertTrue(cardHandOut.currArrayList
                == Card.getSuspectsArrayList());
    }

    @Test
    public void testArrayListWeaponTrue(){
        cardHandOut.randomlyPickCardOfType(1);
        Assert.assertTrue(cardHandOut.currArrayList
                == Card.getWeaponsArrayList());
    }

    @Test
    public void testArrayListRoomTrue(){
        cardHandOut.randomlyPickCardOfType(2);
        Assert.assertTrue(cardHandOut.currArrayList
                == Card.getRoomsArrayList());
    }

    @Test
    public void testArrayListSuspectWrong(){
        cardHandOut.randomlyPickCardOfType(0);
        Assert.assertFalse(cardHandOut.currArrayList
                == Card.getRoomsArrayList());
    }

    @Test
    public void testArrayListWeaponWrong(){
        cardHandOut.randomlyPickCardOfType(1);
        Assert.assertFalse(cardHandOut.currArrayList
                == Card.getSuspectsArrayList());
    }

    @Test
    public void testArrayListRoomWrong(){
        cardHandOut.randomlyPickCardOfType(2);
        Assert.assertFalse(cardHandOut.currArrayList
                == Card.getWeaponsArrayList());
    }

}
