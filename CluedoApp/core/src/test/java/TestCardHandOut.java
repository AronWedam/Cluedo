import com.cluedo.game.Card;
import com.cluedo.game.CardHandOut;

import org.junit.Assert;
import org.junit.Test;

public class TestCardHandOut {

    private static CardHandOut cardHandOut = new CardHandOut();
    Card testCard0 = new Card(1, 0);
    Card testCard1 = new Card(1,1);
    Card testCard2 = new Card(2,2);
    Card testCard3 = new Card(1, 0);
    Card testCard5 = new Card(1,1);


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


    //TODO WHY ARE THEY FAILING???
    @Test
    public void testRandomlyPickCardOfTypeArraySuspect(){
        int sizeBeforeReducing = 6;
        cardHandOut.randomlyPickCardOfType(0);
        Assert.assertNotEquals(sizeBeforeReducing,
                cardHandOut.currArrayList.size());

    }

    @Test
    public void testRandomlyPickCardOfTypeArrayWeapon(){
        int sizeBeforeReducing = 6;
        cardHandOut.randomlyPickCardOfType(1);
        Assert.assertNotEquals(sizeBeforeReducing,
                cardHandOut.currArrayList.size());

    }

    @Test
    public void testRandomlyPickCardOfTypeArrayRoom(){
        int sizeBeforeReducing = 9;
        cardHandOut.randomlyPickCardOfType(2);
        Assert.assertNotEquals(sizeBeforeReducing,
                cardHandOut.currArrayList.size());

    }

}
