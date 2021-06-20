import com.cluedo.game.network.ConnectionService;

import org.junit.Assert;
import org.junit.Test;

public class TestConnectionService {
    private ConnectionService connectionService = ConnectionService.GetInstance();

    @Test
    public void RegisterForGameTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();
        Assert.assertNotNull(connectionService.GetPlayerId());
    }

    @Test
    public void CheckRegistrationTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        testThread.start();
        testThread.join();
        Assert.assertNotNull(connectionService.GetGame());
    }

    @Test
    public void GetGameTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        testThread.start();
        testThread.join();
        Assert.assertNotNull(connectionService.getPlayers());
        Assert.assertTrue(connectionService.getPlayers().size() > 0);
    }

    @Test
    public void PostNewPositionTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.PostNewPosition(200, 300);
            }
        });
        testThread.start();
        testThread.join();


        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();

        Assert.assertTrue(connectionService.getCurrentPlayer().getX() == 200);
        Assert.assertTrue(connectionService.getCurrentPlayer().getY() == 300);
    }
}
