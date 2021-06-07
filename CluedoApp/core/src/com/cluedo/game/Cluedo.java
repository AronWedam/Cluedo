package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cluedo.game.network.NetworkPlayer;

import java.util.ArrayList;
import java.util.List;

public class Cluedo implements Screen, GestureDetector.GestureListener{
    private GameClass game;
    private com.cluedo.game.network.ConnectionService connectionService;
    private Player player;
    Rectangle piece;

    private List<Player> players;
    private List<Rectangle> pieces;

    GestureDetector gestureDetector;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private TiledMap map;
    public CluedoMap cluedoMap = new CluedoMap();

    float currentZoom;

    private SpriteBatch Notebookbatch;
    Notebook notebook;
    private Player currentPlayer;

    public Cluedo(final GameClass game) throws InterruptedException {
        this.game = game;
        players = new ArrayList<>();
        pieces = new ArrayList<>();
        //Get the Players of the Current Game
        connectionService = com.cluedo.game.network.ConnectionService.GetInstance();

        map = new TmxMapLoader().load("maps/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //create gestureDetector
        gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);

        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        //batch for the viewportNotebook method
        Notebookbatch = new SpriteBatch();

        getPlayers();
        notebook = new Notebook(currentPlayer);

        currentPlayer.setMyRoomCard();
        currentPlayer.setMySuspectCard();
        currentPlayer.setMyWeaponCard();

        notebook.yourSuspectCard();
        notebook.yourRoomCards();
        notebook.yourWeaponCard();
    }

    private void SyncNetworkPlayersWithGamePlayers() {
        List<Player> tempPlayers = new ArrayList<>();
        List<Rectangle> tempRectange = new ArrayList<>();

        for(NetworkPlayer currentPlayer : connectionService.getPlayers()) {
            if (currentPlayer.getId().equals(connectionService.GetPlayerId())) {
                piece = new Rectangle();
                piece.x = currentPlayer.getX();
                piece.y = currentPlayer.getY();
                piece.width = 32;
                piece.height = 32;

                //create the player
                player = new Player(new Texture(currentPlayer.getPlayerImage()), cluedoMap, (int) piece.x, (int) piece.y);
                this.currentPlayer = player;
                connectionService.setCurrentPlayer(currentPlayer);

                tempPlayers.add(player);
                tempRectange.add(piece);
            } else {
                //create a Rectangle to logically represent one player
                Rectangle rect = new Rectangle();
                rect.x = currentPlayer.getX();
                rect.y = currentPlayer.getY();
                rect.width = 32;
                rect.height = 32;

                //create the player
                Player otherPlayer = new Player(new Texture(currentPlayer.getPlayerImage()), cluedoMap, (int) rect.x, (int) rect.y);
                tempPlayers.add(otherPlayer);
                tempRectange.add(rect);
            }
        }
        players = tempPlayers;
        pieces = tempRectange;
    }

    private void getPlayers() throws InterruptedException {
        Thread GetGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();
            }
        });
        GetGameThread.start();
        GetGameThread.join();
        SyncNetworkPlayersWithGamePlayers();
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        //clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set up map and camera
        renderer.setView(camera);
        renderer.render();
        camera.update();

        mapViewport();
        mapNotebook();

        game.batch.setProjectionMatrix(camera.combined);

        if(!Gdx.input.justTouched()) {
            try {
                getPlayers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Draw the players
        for (int i=0; i<players.size(); i++) {
            Player currentPlayer = players.get(i);
            Rectangle currentPiece = pieces.get(i);
            currentPlayer.render(camera, game.batch, currentPlayer.getX(), currentPlayer.getY(), currentPiece.width, currentPiece.height);
        }

        //Single Touch enables player Movement for 1 Tile
        if(Gdx.input.justTouched() && Gdx.input.getX(0) > Gdx.graphics.getWidth()/3) {
            double x = Gdx.input.getX(0) - (Gdx.graphics.getWidth()/3);
            double y = Gdx.input.getY(0);

            //TODO first if doesn't work properly
            //Check if Player figure is touched - doesn't work at all
            if(x >= player.getX()-1 || x <= player.getX()+1 && y >= player.getY()-1 || y <= player.getY()+1) {
                //Save Coordinates of Touched Area
                if(Gdx.input.justTouched()) {
                    Vector3 touchPos = new Vector3();
                    touchPos.set((float) x, Gdx.input.getY(0), 0);
                    camera.unproject(touchPos);

                    //Make move in touched direction
                    if(touchPos.x > player.getX() || touchPos.y > player.getY()) {
                        if (touchPos.x > player.getX()) {
                            player.setPos((int) player.getX() + 32, player.getY());
                            if (touchPos.y > player.getY()) {
                                player.setPos((int) player.getX(), player.getY() + 32);
                            }
                        } else if (touchPos.y > player.getY()) {
                            player.setPos((int) player.getX(), player.getY() + 32);
                        }
                    }

                    if(touchPos.x < player.getX() || touchPos.y < player.getY()) {
                        if (touchPos.x < player.getX()) {
                            player.setPos((int) player.getX() - 32, player.getY());
                            if (touchPos.y < player.getY()) {
                                player.setPos((int) player.getX(), player.getY() - 32);
                            }
                        } else
                            if (touchPos.y < player.getY()) {
                                player.setPos((int) player.getX(), player.getY() - 32);
                        }
                    }
                }
            }
        } else if (Gdx.input.justTouched() && Gdx.input.getX(0) < Gdx.graphics.getWidth()/3) {
            double y = Gdx.input.getY(0);
            int row = notebook.table.getRow((float) (Gdx.graphics.getHeight() - y));
            Gdx.app.log("Row", "Row: " + row);
            Actor actor = notebook.table.getChild(row);
            Gdx.app.log("Class", actor.getClass().getName());

            if (actor instanceof TextButton) {
                TextButton myButton = (TextButton) actor;
                String clickedName = myButton.getText().toString();

                switch (clickedName) {
                    case "Finish Move":
                        if (connectionService.getCurrentPlayer() != null && connectionService.getCurrentPlayer().getMaywalk()) {
                            Thread finishMoveThread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    connectionService.FinishMove();
                                }
                            });
                            finishMoveThread.start();
                            try {
                                finishMoveThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    break;

                    default:
                        break;
                }

                Gdx.app.log("Button Text", myButton.getText().toString());
                Gdx.app.log("Button label", myButton.getLabel().toString());
            }
        }
    }

    private void mapViewport(){
        Gdx.gl.glViewport(0,0, (int) (Gdx.graphics.getWidth()/1.5),
                Gdx.graphics.getHeight());
    }

    private void mapNotebook() {
        notebook.getPane().setBounds(0, 0, Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight());


        Notebookbatch.begin();
        notebook.getPane().draw(Notebookbatch, 1);
        notebook.table.draw(Notebookbatch, 1);
        Notebookbatch.end();


        Gdx.gl.glViewport(Gdx.graphics.getWidth() / 3, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        map.dispose();

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        currentZoom = camera.zoom;
        Gdx.app.log("INFO", "x:" + x + "y:" + y);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX/2, deltaY/2);
        camera.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("INFO", "panStop");
        currentZoom = camera.zoom;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        camera.zoom = (initialDistance / distance) * currentZoom;
        camera.update();
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
