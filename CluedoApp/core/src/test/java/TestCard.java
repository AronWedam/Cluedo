import com.cluedo.game.Card;

import org.junit.Assert;
import org.junit.Test;

public class TestCard {

    Card testCard0 = new Card(1, 0);
    Card testCard1 = new Card(1,1);
    Card testCard2 = new Card(2,2);
    Card testCard3 = new Card(1, 0);
    Card testCard5 = new Card(1,1);
    Card testCard6 = new Card(0,0);

    @Test
    public void testGetValueRight(){
        Assert.assertEquals(0, testCard0.getValue());
        Assert.assertEquals(1, testCard1.getValue());
    }

    @Test
    public void testGetValueWrong(){
        Assert.assertNotEquals(2, testCard1.getValue());
        Assert.assertNotEquals(0, testCard2.getValue());
    }

    @Test
    public void testEqualsTrue(){
        Assert.assertTrue(testCard0.equals(testCard3));
        Assert.assertTrue(testCard1.equals(testCard5));
    }

    @Test
    public void testEqualsFalse(){
        Assert.assertFalse(testCard0.equals(testCard1));
        Assert.assertFalse(testCard2.equals(testCard5));
    }

    @Test
    public void testGetTypeRight(){
        Assert.assertEquals(1, testCard0.getType());
        Assert.assertEquals(2, testCard2.getType());
    }

    @Test
    public void testGetTypeWrong(){
        Assert.assertNotEquals(1, testCard2.getType());
        Assert.assertNotEquals(2, testCard6.getType());
    }
}
