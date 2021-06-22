import com.cluedo.game.Murderer;
import org.junit.Test;

public class TestMurderer {

    @Test(expected = NullPointerException.class)
    public void testNullPointerException(){
        Murderer.isActuallyTheMurderer(null, null, null);
    }

}
