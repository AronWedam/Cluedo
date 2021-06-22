import com.cluedo.game.network.ConnectionService;
import com.cluedo.game.network.NetworkPlayer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConnectionService {
    private ConnectionService connectionService = ConnectionService.GetInstance();

    @Test
    public void AAResetGameBefore() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.ResetGame();
            }
        });
        testThread.start();
        testThread.join();
    }

    @Test
    public void ARegisterForGameTest() throws InterruptedException {
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
    public void BCheckRegistrationTest() throws InterruptedException {
        Thread.sleep(10000);

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();

        Assert.assertNotNull(connectionService.getPlayers());
        Assert.assertNotNull(connectionService.getSuspect());
        Assert.assertNotNull(connectionService.getWeapon());
        Assert.assertNotNull(connectionService.getRoom());
    }

    @Test
    public void CGetGameTest() throws InterruptedException {
        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();
        Assert.assertNotNull(connectionService.getPlayers());
    }

    @Test
    public void DPostNewPositionTest() throws InterruptedException {
        Thread postThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.PostNewPosition(200, 300);
            }
        });
        postThread.start();
        postThread.join();

        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();

        for (NetworkPlayer networkPlayer : connectionService.getPlayers()) {
            if (networkPlayer.getId().equals(connectionService.GetPlayerId()))
                connectionService.setCurrentPlayer(networkPlayer);
        }
    }

    @Test
    public void EFinishMoveTest() throws InterruptedException {
        Thread finishMoveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.FinishMove();
            }
        });
        finishMoveThread.start();
        finishMoveThread.join();

        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();

        for (NetworkPlayer networkPlayer : connectionService.getPlayers()) {
            if (networkPlayer.getId().equals(connectionService.GetPlayerId()))
                connectionService.setCurrentPlayer(networkPlayer);
        }

        Assert.assertFalse(connectionService.getCurrentPlayer().getMaywalk());
    }

    @Test
    public void ZResetGameAfter() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.ResetGame();
            }
        });
        testThread.start();
        testThread.join();
    }
}
