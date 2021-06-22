import com.badlogic.gdx.math.Vector2;
import com.cluedo.game.CluedoMap;

import org.junit.Assert;
import org.junit.Test;

public class TestCluedoMap {

    @Test
    public void testSetup() {
        CluedoMap map = new CluedoMap();
        Vector2 vector = new Vector2();
        vector.x = 5*32;
        vector.y = 5*32;

        Assert.assertEquals(vector, map.mapconstants[5][5]);
    }
}
