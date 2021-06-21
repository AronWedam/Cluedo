import com.cluedo.game.network.ConnectionService;
import com.cluedo.game.network.NetworkPlayer;

import org.junit.After;
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
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();
        Assert.assertTrue(connectionService.getPlayers().size() > 0);
        Assert.assertNotNull(connectionService.getRoom());
        Assert.assertNotNull(connectionService.getSuspect());
        Assert.assertNotNull(connectionService.getWeapon());
    }

    @Test
    public void GetGameTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();

        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();
        Assert.assertNotNull(connectionService.getPlayers());
        Assert.assertTrue(connectionService.getPlayers().size() > 0);
    }

    @Test
    public void PostNewPositionTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();

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

        Assert.assertTrue(connectionService.getCurrentPlayer().getX() == 200);
        Assert.assertTrue(connectionService.getCurrentPlayer().getY() == 300);
    }

    @Test
    public void FinishMoveTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();

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
    public void FinishGameTest() throws InterruptedException {
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.RegisterForGame("TestUserName");
            }
        });
        testThread.start();
        testThread.join();

        Thread checkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.CheckRegistration();
            }
        });
        checkThread.start();
        checkThread.join();

        Thread finishGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.FinishGame();
            }
        });
        finishGameThread.start();
        finishGameThread.join();

        Thread getGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        getGameThread.start();
        getGameThread.join();

        Assert.assertTrue(connectionService.isGameOver());
    }

    @After
    public void ResetGame() throws InterruptedException {
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
